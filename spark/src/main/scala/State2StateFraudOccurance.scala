import com.datastax.spark.connector._
import org.apache.spark.{SparkConf, SparkContext}

//the object is used to load talbes that will store information the number of occurrence of cases where a creditcard was used in stateA and then in stateB
object CreditFraudDetection {
  def main(args: Array[String]) {

    //    Create Spark Context
    val conf = new SparkConf(true).setAppName("RollupRetail")

    // We set master on the command line for flexibility
    val sc = new SparkContext(conf)

    val receipts_by_creditcard = sc.cassandraTable("retail","receipts_by_credit_card").select("receipt_id","credit_card_number","store_id","receipt_timestamp").keyBy(row => row.getString("store_id"))
    val stores =  sc.cassandraTable("retail","stores").select("store_id","state").keyBy(row => row.getString("store_id"))
    val creditcard_by_store = receipts_by_creditcard.join(stores).persist()

    //gets the number of fraud occuring between any two states
    val creditcard_by_store_unpacked = creditcard_by_store.map({case (store_id,(result1, result2)) => (result1.getString("credit_card_number"), result2.getString("state"))})
    val creditcard_by_store_self_join = creditcard_by_store_unpacked.join(creditcard_by_store_unpacked)
    val state2state_fraud = creditcard_by_store_self_join.map{case (store_id,(state1, state2)) => ((state1,state2),store_id)}.filter{ case ((state1,state2),store_id) => state1 != state2}.countByKey().filter{ case (k,v) => v > 1}.map{ case ((state1,state2),num_fraud) => (state1,state2 + "_",num_fraud)}
    val state2state_fraud_filter = state2state_fraud.filter( t= > filter(t._3 > 100))
    sc.parallelize(state2state_fraud_filter.toList).saveToCassandra("retail","state_to_state_fraud",SomeColumns("state1","state2","num_fraud"))
  }
}