import com.datastax.spark.connector._
import org.apache.spark.{SparkConf, SparkContext}

object CreditFraudDetection {
  def main(args: Array[String]) {

    //    Create Spark Context
    val conf = new SparkConf(true).setAppName("RollupRetail")

    // We set master on the command line for flexibility
    val sc = new SparkContext(conf)

    val receipts_by_creditcard = sc.cassandraTable("retail","receipts_by_credit_card").select("receipt_id","credit_card_number","store_id","receipt_timestamp").keyBy(row => row.getString("store_id"))
    val stores =  sc.cassandraTable("retail","stores").select("store_id","state").keyBy(row => row.getString("store_id"))
    val creditcard_by_store = receipts_by_creditcard.join(stores).persist()

    //gets the credit card that were being used fraudulently (use int two different states)
    val creditcard_fraud= creditcard_by_store.map{ case (store_id,(result1,result2)) => (result1.getString("credit_card_number"),result2.getString("state"))}.distinct.countByKey().filter{ case (k,v) => v > 1}
    sc.parallelize(creditcard_fraud.toList).saveToCassandra("retail","credit_card_fraud_list",SomeColumns("credit_card_number","state_num"))
  }
}
