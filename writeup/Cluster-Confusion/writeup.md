
# Cluster-Confusion

#### Team
<ol>
        <li> Hamdan Ahmad </li>
        <li> Dohar Silalahi </li>
        <li> Ravi Akkineni </li>
</ol>

## Purpose

The purpose of this excercise was to get the a mis-behaving cluster back up on its feet. We started out investigating how the the cluster was performing.

## Investigation

### JMeter Installation
```sh
# download JMeter
wget https://s3.amazonaws.com/datastaxtraining/jmeter/apache-jmeter-2.13.2.zip

unzip apache-jmeter-2.13.2.zip
```

### Results from JMeter
```sh
Creating summariser <summary>
Created the tree successfully using confusion.jmx
Starting the test @ Mon Nov 16 21:37:46 UTC 2015 (1447709866211)
Waiting for possible shutdown message on port 4445

0: Write trades                                32 in  11.5s =    2.8/s Avg:   240 Min:     3 Max:  1770 Err:    32 (100.00%)
1: Write trades_by_tickerday                   32 in    10s =    3.2/s Avg:   100 Min:     1 Max:   679 Err:     0 (0.00%)
3: Write trades_by_datehour                    31 in   9.1s =    3.4/s Avg:   285 Min:   161 Max:  1002 Err:     0 (0.00%)
4: Trade by id                                 16 in     9s =    1.8/s Avg:   438 Min:   163 Max:  1148 Err:     0 (0.00%)
5: 10 Minute Range By Ticker First 10          22 in  10.2s =    2.2/s Avg:   489 Min:   164 Max:  1884 Err:     1 (4.55%)
6: 10 Minute Count By Ticker                   13 in   7.2s =    1.8/s Avg:   481 Min:   163 Max:  1119 Err:     1 (7.69%)
7: 1 Minute Range By Hour First 10             12 in   7.4s =    1.6/s Avg:   440 Min:   173 Max:   767 Err:     1 (8.33%)
summary:                                 +    158 in    12s =   13.6/s Avg:   310 Min:     1 Max:  1884 Err:    35 (22.15%) Active: 8 Started: 8                                                                                              Finished: 0

0: Write trades                               269 in    30s =    9.0/s Avg:   185 Min:     1 Max:   436 Err:   269 (100.00%)
1: Write trades_by_tickerday                  269 in    30s =    9.0/s Avg:    89 Min:     1 Max:   313 Err:     0 (0.00%)
3: Write trades_by_datehour                   266 in    30s =    8.9/s Avg:   299 Min:   160 Max:   593 Err:     0 (0.00%)
4: Trade by id                                119 in    30s =    4.0/s Avg:   492 Min:   162 Max:  1434 Err:     0 (0.00%)
5: 10 Minute Range By Ticker First 10         133 in  29.1s =    4.6/s Avg:   501 Min:   162 Max:  1404 Err:     0 (0.00%)
6: 10 Minute Count By Ticker                  111 in    31s =    3.6/s Avg:   554 Min:   162 Max:  1433 Err:     0 (0.00%)
7: 1 Minute Range By Hour First 10            121 in    32s =    3.8/s Avg:   945 Min:     1 Max:  5945 Err:    15 (12.40%)
summary:                                 +   1288 in    32s =   40.3/s Avg:   353 Min:     1 Max:  5945 Err:   284 (22.05%) Active: 18 Started:                                                                                              18 Finished: 0
summary:                                 =   1446 in    42s =   34.7/s Avg:   348 Min:     1 Max:  5945 Err:   319 (22.06%)

0: Write trades                               313 in  30.1s =   10.4/s Avg:   178 Min:     1 Max:   405 Err:   313 (100.00%)
1: Write trades_by_tickerday                  311 in    30s =   10.4/s Avg:    85 Min:     0 Max:   314 Err:     0 (0.00%)
3: Write trades_by_datehour                   312 in  30.4s =   10.3/s Avg:   315 Min:   160 Max:   607 Err:     0 (0.00%)
4: Trade by id                                134 in  29.5s =    4.5/s Avg:   520 Min:   162 Max:  1270 Err:     0 (0.00%)
5: 10 Minute Range By Ticker First 10         179 in  30.5s =    5.9/s Avg:   497 Min:     1 Max:  1421 Err:     6 (3.35%)
6: 10 Minute Count By Ticker                  155 in  30.1s =    5.2/s Avg:   578 Min:     2 Max:  1602 Err:     3 (1.94%)
7: 1 Minute Range By Hour First 10            146 in  30.3s =    4.8/s Avg:   700 Min:   161 Max:  5162 Err:     7 (4.79%)
summary:                                 +   1550 in    31s =   50.7/s Avg:   342 Min:     0 Max:  5162 Err:   329 (21.23%) Active: 18 Started: 18 Finished: 0
summary:                                 =   2996 in    72s =   41.8/s Avg:   345 Min:     0 Max:  5945 Err:   648 (21.63%)

0: Write trades                               307 in    30s =   10.2/s Avg:   181 Min:     1 Max:   452 Err:   307 (100.00%)
1: Write trades_by_tickerday                  308 in    30s =   10.3/s Avg:    84 Min:     0 Max:   231 Err:     0 (0.00%)
3: Write trades_by_datehour                   309 in  30.1s =   10.3/s Avg:   316 Min:   161 Max:   627 Err:     0 (0.00%)
4: Trade by id                                159 in  30.3s =    5.3/s Avg:   516 Min:   161 Max:  1245 Err:     1 (0.63%)
5: 10 Minute Range By Ticker First 10         170 in    31s =    5.6/s Avg:   528 Min:   162 Max:  1584 Err:     1 (0.59%)
6: 10 Minute Count By Ticker                  144 in  29.4s =    4.9/s Avg:   495 Min:     2 Max:  1439 Err:     2 (1.39%)
7: 1 Minute Range By Hour First 10            183 in    34s =    5.4/s Avg:   676 Min:     1 Max:  5259 Err:     8 (4.37%)
summary:                                 +   1580 in  34.2s =   46.2/s Avg:   345 Min:     0 Max:  5259 Err:   319 (20.19%) Active: 18 Started: 18 Finished: 0
summary:                                 =   4576 in   102s =   45.0/s Avg:   345 Min:     0 Max:  5945 Err:   967 (21.13%)

0: Write trades                               301 in  30.1s =   10.0/s Avg:   176 Min:     1 Max:   917 Err:   301 (100.00%)
1: Write trades_by_tickerday                  300 in  30.1s =   10.0/s Avg:    95 Min:     0 Max:   314 Err:     0 (0.00%)
3: Write trades_by_datehour                   299 in  30.4s =    9.8/s Avg:   329 Min:   160 Max:  1415 Err:     0 (0.00%)
4: Trade by id                                155 in    31s =    5.1/s Avg:   518 Min:   161 Max:  1892 Err:     2 (1.29%)
5: 10 Minute Range By Ticker First 10         158 in    31s =    5.2/s Avg:   552 Min:   161 Max:  2409 Err:     2 (1.27%)
6: 10 Minute Count By Ticker                  163 in    31s =    5.3/s Avg:   493 Min:   161 Max:  1553 Err:     2 (1.23%)
7: 1 Minute Range By Hour First 10            149 in    31s =    4.8/s Avg:   759 Min:   161 Max:  5161 Err:     6 (4.03%)
summary:                                 +   1525 in    31s =   49.2/s Avg:   354 Min:     0 Max:  5161 Err:   313 (20.52%) Active: 18 Started: 18 Finished: 0
summary:                                 =   6101 in   132s =   46.4/s Avg:   348 Min:     0 Max:  5945 Err:  1280 (20.98%)
^Z
[1]+  Stopped                 jmeter -n -t confusion.jmx

```
### Nodetool Status

```sh
Datacenter: nearby
==================
Status=Up/Down
|/ State=Normal/Leaving/Joining/Moving
--  Address         Load       Tokens  Owns    Host ID                               Rack
UN  52.34.61.114    11.88 GB   1       ?       6434b17f-6429-46c8-9bcf-c6c89a99bf6d  RAC1
DN  52.33.99.98     8.98 GB    1       ?       fd8d92ac-37c3-4b62-b01d-f8fd717f7564  RAC1
UN  52.34.61.4      6.11 GB    1       ?       c0721311-1a12-4820-9e4f-73c92c398daa  RAC1
UN  52.32.185.187   10.11 GB   1       ?       8962d934-00e5-44ba-9104-6b4c0627f20c  RAC1
DN  52.34.52.77     7.42 GB    1       ?       e5d1b9f6-0768-431f-ad88-d64bb8c65395  RAC1
Datacenter: faraway
===================
Status=Up/Down
|/ State=Normal/Leaving/Joining/Moving
--  Address         Load       Tokens  Owns    Host ID                               Rack
UN  54.169.193.107  7.72 GB    1       ?       31cb10ee-8242-4c30-a07d-d542bc2dd51a  RAC1
?N  172.31.0.8      11.11 GB   1       ?       6fae860f-041d-4db4-bbb9-d03d0cc909b7  RAC1
UN  54.169.187.209  6.53 MB    1       ?       1af77c8d-d5a9-4770-a6dc-a618c8eafa6f  RAC1
UN  54.169.185.35   10.88 GB   1       ?       c788eba1-be1e-407d-a442-a4d765563908  RAC1
```

### Initial Assessment

There were some evident issues with the the cluster. Two nodes were down, one node had an unknown status being reported by the node we ran the nodetool status on and the DC nearby was not balanced.

## Fixing Nodes

### Fixing Downed Node 52.34.52.77

While trying to restart the node we found the below error being reported in System.log for the node

```sh
ERROR 22:06:46 Exception encountered during startup
java.lang.RuntimeException: Cannot replace address with a node that is already bootstrapped
        at org.apache.cassandra.service.StorageService.prepareToJoin(StorageService.java:774) ~[apache-cassandra-2.1.9.jar:2.1.9]
        at org.apache.cassandra.service.StorageService.initServer(StorageService.java:720) ~[apache-cassandra-2.1.9.jar:2.1.9]
        at org.apache.cassandra.service.StorageService.initServer(StorageService.java:611) ~[apache-cassandra-2.1.9.jar:2.1.9]
        at org.apache.cassandra.service.CassandraDaemon.setup(CassandraDaemon.java:378) [apache-cassandra-2.1.9.jar:2.1.9]
        at org.apache.cassandra.service.CassandraDaemon.activate(CassandraDaemon.java:537) [apache-cassandra-2.1.9.jar:2.1.9]
        at org.apache.cassandra.service.CassandraDaemon.main(CassandraDaemon.java:626) [apache-cassandra-2.1.9.jar:2.1.9]
java.lang.RuntimeException: Cannot replace address with a node that is already bootstrapped
        at org.apache.cassandra.service.StorageService.prepareToJoin(StorageService.java:774)
        at org.apache.cassandra.service.StorageService.initServer(StorageService.java:720)
        at org.apache.cassandra.service.StorageService.initServer(StorageService.java:611)
        at org.apache.cassandra.service.CassandraDaemon.setup(CassandraDaemon.java:378)
        at org.apache.cassandra.service.CassandraDaemon.activate(CassandraDaemon.java:537)
        at org.apache.cassandra.service.CassandraDaemon.main(CassandraDaemon.java:626)
Exception encountered during startup: Cannot replace address with a node that is already bootstrapped
```
The above indicated we were asking Cassandra to replcae a node that had already been used to replace another node and most likely we did not comment out the -Dcassandra.repalce_address property in the cassandra-env.sh file

We started off be removing the node from the cluster (although we now realized that this was most likely not needed)
```sh
root@node0:/etc/cassandra# nodetool removenode  e5d1b9f6-0768-431f-ad88-d64bb8c65395
```

We then proceeded to remove the following line from cassandra-env.sh:
```
JVM_OPTS="$JVM_OPTS -Dcassandra.replace_address=123.123.123.123"
```

After which we proceeded with a restart for the node
```sh
root@node3:/usr/sbin# ./cassandra -f
```

This brought the node back up successfully

### Fixing issue with Node: 52.33.99.98

When trying to restart this node we saw the below error in the System.log file

#### Issue
```sh
ERROR 22:29:52 Exception in thread Thread[MemtableFlushWriter:1,5,main]
java.lang.RuntimeException: Insufficient disk space to write 211 bytes
        at org.apache.cassandra.io.util.DiskAwareRunnable.getWriteDirectory(DiskAwareRunnable.java:29) ~[apache-cassandra-2.1.9.jar:2.1.9]
        at org.apache.cassandra.db.Memtable$FlushRunnable.runMayThrow(Memtable.java:332) ~[apache-cassandra-2.1.9.jar:2.1.9]
        at org.apache.cassandra.utils.WrappedRunnable.run(WrappedRunnable.java:28) ~[apache-cassandra-2.1.9.jar:2.1.9]
        at com.google.common.util.concurrent.MoreExecutors$SameThreadExecutorService.execute(MoreExecutors.java:297) ~[guava-16.0.jar:na]
        at org.apache.cassandra.db.ColumnFamilyStore$Flush.run(ColumnFamilyStore.java:1154) ~[apache-cassandra-2.1.9.jar:2.1.9]
        at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142) ~[na:1.8.0_66]
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617) ~[na:1.8.0_66]
```
This indiciated that there was no more disk space present for cassandra to write to. In order to fix this we needed to figure out what the current disk utilization looked like for the file system.

#### Investigating the issue
```sh
root@node1:/etc/cassandra# df -h
Filesystem                       Size  Used Avail Use% Mounted on
/dev/xvda                        9.8G  1.7G  7.6G  18% /
none                             4.0K     0  4.0K   0% /sys/fs/cgroup
udev                             3.7G   12K  3.7G   1% /dev
tmpfs                            752M  216K  752M   1% /run
none                             5.0M     0  5.0M   0% /run/lock
none                             3.7G     0  3.7G   0% /run/shm
none                             100M     0  100M   0% /run/user
/dev/mapper/vg--data-ephemeral0   30G   28G     0 100% /mnt/ephemeral
```
Based on the above we realized that the mnt/ephemeral file system, that node is using to write to is completely full. Therefore we needed to free up space

#### Fix the issue
```
root@node1:/etc/cassandra# cd /mnt/ephemeral/
root@node1:/mnt/ephemeral# ls
cassandra  lost+found
root@node1:/mnt/ephemeral# cd cassandra/
root@node1:/mnt/ephemeral/cassandra# ls
commitlog  data  saved_caches
root@node1:/mnt/ephemeral/cassandra# ls -al
total 19760376
drwxr-xr-x 5 cassandra cassandra        4096 Nov 16 01:14 .
drwxr-xr-x 4 root      root             4096 Nov 15 22:34 ..
drwxr-xr-x 2 cassandra cassandra        4096 Nov 16 22:54 commitlog
drwxr-xr-x 6 cassandra cassandra        4096 Nov 15 23:19 data
-rw-r--r-- 1 root      root      20234567680 Nov 16 01:14 .hugefile
drwxr-xr-x 2 cassandra cassandra        4096 Nov 15 22:36 saved_caches
root@node1:/mnt/ephemeral/cassandra# rm .hugefile
```

After making the above change and restarting the node we were able to bring the node back up

### Fixing issue with Node: 52.34.52.77 

When running 'nodetool status' on node 52.34.52.77 it indicated that it was unable to Gossip correctly with any of the nodes in the 'nearby' data center (Shown below). And other nodes reported that they did not know the status of node 52.34.52.77. This indiciated there was an issue with node 52.34.52.77 with respect to Gossip. On going through the cassandra.yaml file we realized that node 52.34.52.77 was broadcast its private addres (172.31.0.8) to other nodes.

```sh
root@node5:~# nodetool status
Datacenter: nearby
==================
Status=Up/Down
|/ State=Normal/Leaving/Joining/Moving
--  Address         Load       Tokens  Owns    Host ID                               Rack
DN  52.34.61.114    13.46 GB   1       ?       6434b17f-6429-46c8-9bcf-c6c89a99bf6d  RAC1
DN  52.33.99.98     8.99 GB    1       ?       fd8d92ac-37c3-4b62-b01d-f8fd717f7564  RAC1
DN  52.34.61.4      6.11 GB    1       ?       c0721311-1a12-4820-9e4f-73c92c398daa  RAC1
DN  52.32.185.187   13.28 GB   1       ?       8962d934-00e5-44ba-9104-6b4c0627f20c  RAC1
?N  52.34.52.77     7.42 GB    1       ?       e5d1b9f6-0768-431f-ad88-d64bb8c65395  RAC1
Datacenter: faraway
===================
Status=Up/Down
|/ State=Normal/Leaving/Joining/Moving
--  Address         Load       Tokens  Owns    Host ID                               Rack
UN  54.169.193.107  7.72 GB    1       ?       31cb10ee-8242-4c30-a07d-d542bc2dd51a  RAC1
UN  172.31.0.8      11.11 GB   1       ?       6fae860f-041d-4db4-bbb9-d03d0cc909b7  RAC1
UN  54.169.187.209  57.97 MB   1       ?       1af77c8d-d5a9-4770-a6dc-a618c8eafa6f  RAC1
UN  54.169.185.35   10.88 GB   1       ?       c788eba1-be1e-407d-a442-a4d765563908  RAC1
```
On investigateing the Cassandra.yaml file noticed the below line commented out. Which meant the node defaulted to the listen_address which was set to its prviate ip.
```
# Address to broadcast to other Cassandra nodes
# Leaving this blank will set it to the same value as listen_address
#broadcast_address: 54.169.180.21
```
After uncommenting the above line, stopped and restarting cassandra node the node was back up again 

```sh
root@node5:~# nodetool status
Datacenter: nearby
==================
Status=Up/Down
|/ State=Normal/Leaving/Joining/Moving
--  Address         Load       Tokens  Owns    Host ID                               Rack
UN  52.34.61.114    13.46 GB   1       ?       6434b17f-6429-46c8-9bcf-c6c89a99bf6d  RAC1
UN  52.33.99.98     8.99 GB    1       ?       fd8d92ac-37c3-4b62-b01d-f8fd717f7564  RAC1
UN  52.34.61.4      6.11 GB    1       ?       c0721311-1a12-4820-9e4f-73c92c398daa  RAC1
UN  52.32.185.187   13.28 GB   1       ?       8962d934-00e5-44ba-9104-6b4c0627f20c  RAC1
UN  52.34.52.77     7.42 GB    1       ?       e5d1b9f6-0768-431f-ad88-d64bb8c65395  RAC1
Datacenter: faraway
===================
Status=Up/Down
|/ State=Normal/Leaving/Joining/Moving
--  Address         Load       Tokens  Owns    Host ID                               Rack
UN  54.169.193.107  7.72 GB    1       ?       31cb10ee-8242-4c30-a07d-d542bc2dd51a  RAC1
UN  54.169.187.209  57.97 MB   1       ?       1af77c8d-d5a9-4770-a6dc-a618c8eafa6f  RAC1
UN  54.169.185.35   10.88 GB   1       ?       c788eba1-be1e-407d-a442-a4d765563908  RAC1
UN  54.169.180.21   11.12 GB   1       ?       6fae860f-041d-4db4-bbb9-d03d0cc909b7  RAC1

Note: Non-system keyspaces don't have the same replication settings, effective ownership information is meaningless
```
Ran a repair on the node to fix any issues it not able to gossip correctly might have caused
```sh
root@node5:/etc/cassandra# nodetool repair -pr
```

### Issue with node8 (54.169.187.209)

When running Jmeter test we realized that Jmeter was showing a high degree of error messages on some of the tasks. On viewing the reason behind them we realizd that there were requires that were unable to meet consistency of QUORUM. 

Analysis of the cluster through OpsCenter displayed that node8 was much smaller then the other nodes. On analysis of the cassandra.yaml file we noticed that the autoboot strap configuration was turned off. Which meant that the node had not received any streaming data from the other nodes. To fix this we ran *nodetool rebuild* and changed the autoboot strap setting to be true (incase of future restarts that required it to receive streamed data.

After making the above changes all the errors in Jmeter were gone.


## Fix Performance Issues

### DataCenter 'nearby' for keyspace 'stock' unbalanced

Below 'Owns' column indicates that the imbalance. We should be be seeing 60% ownership by each node

```sh
root@node5:~# nodetool status stock
Datacenter: nearby
==================
Status=Up/Down
|/ State=Normal/Leaving/Joining/Moving
--  Address         Load       Tokens  Owns (effective)  Host ID                               Rack
UN  52.34.61.114    13.46 GB   1       80.0%             6434b17f-6429-46c8-9bcf-c6c89a99bf6d  RAC1
UN  52.33.99.98     8.99 GB    1       60.0%             fd8d92ac-37c3-4b62-b01d-f8fd717f7564  RAC1
UN  52.34.61.4      6.11 GB    1       40.0%             c0721311-1a12-4820-9e4f-73c92c398daa  RAC1
UN  52.32.185.187   13.28 GB   1       70.0%             8962d934-00e5-44ba-9104-6b4c0627f20c  RAC1
UN  52.34.52.77     7.42 GB    1       50.0%             e5d1b9f6-0768-431f-ad88-d64bb8c65395  RAC1
Datacenter: faraway
===================
Status=Up/Down
|/ State=Normal/Leaving/Joining/Moving
--  Address         Load       Tokens  Owns (effective)  Host ID                               Rack
UN  54.169.193.107  7.72 GB    1       50.0%             31cb10ee-8242-4c30-a07d-d542bc2dd51a  RAC1
UN  54.169.187.209  57.97 MB   1       50.0%             1af77c8d-d5a9-4770-a6dc-a618c8eafa6f  RAC1
UN  54.169.185.35   10.88 GB   1       50.0%             c788eba1-be1e-407d-a442-a4d765563908  RAC1
UN  54.169.180.21   11.12 GB   1       50.0%             6fae860f-041d-4db4-bbb9-d03d0cc909b7  RAC1
```

Using http://jonathanhui.com/cassandra-performance-tuning-and-monitoring twe figured out that the token assignment for a balanced cluster should be:

```
-9223372036854770000
-5534023222112860000
-1844674407370950000
1844674407370950000
5534023222112860000
```
Based on the above we realized that only one node needed to be realigned. And therefore ran the *nodetool move* comamdn
```
root@node4:~# nodetool move 5534023222112860000
```
When the above command completed we checked the status of the node and configured that the cluster was balanced.
```sh
root@node4:/var/log/cassandra# nodetool status stock
Datacenter: nearby
==================
Status=Up/Down
|/ State=Normal/Leaving/Joining/Moving
--  Address         Load       Tokens  Owns (effective)  Host ID                               Rack
UN  52.34.61.114    13.71 GB   1       60.0%             6434b17f-6429-46c8-9bcf-c6c89a99bf6d  RAC1
UN  52.33.99.98     9.07 GB    1       60.0%             fd8d92ac-37c3-4b62-b01d-f8fd717f7564  RAC1
UN  52.34.61.4      9.14 GB    1       60.0%             c0721311-1a12-4820-9e4f-73c92c398daa  RAC1
UN  52.32.185.187   13.43 GB   1       60.0%             8962d934-00e5-44ba-9104-6b4c0627f20c  RAC1
UN  52.34.52.77     8.94 GB    1       60.0%             e5d1b9f6-0768-431f-ad88-d64bb8c65395  RAC1
Datacenter: faraway
===================
Status=Up/Down
|/ State=Normal/Leaving/Joining/Moving
--  Address         Load       Tokens  Owns (effective)  Host ID                               Rack
UN  54.169.193.107  7.86 GB    1       50.0%             31cb10ee-8242-4c30-a07d-d542bc2dd51a  RAC1
UN  54.169.187.209  6.13 GB    1       50.0%             1af77c8d-d5a9-4770-a6dc-a618c8eafa6f  RAC1
UN  54.169.185.35   10.96 GB   1       50.0%             c788eba1-be1e-407d-a442-a4d765563908  RAC1
UN  54.169.180.21   11.15 GB   1       50.0%             6fae860f-041d-4db4-bbb9-d03d0cc909b7  RAC1
```

### Capture Performance Metric (Jmeter)
```sh
root@workstation:~/labwork/jmetertest# jmeter -n -t confusion.jmx
Creating summariser <summary>
Created the tree successfully using confusion.jmx
Starting the test @ Tue Nov 17 15:39:01 UTC 2015 (1447774741036)
Waiting for possible shutdown message on port 4445

0: Write trades                                 1 in     2s =    0.7/s Avg:  152                                                      7 Min:  1527 Max:  1527 Err:     0 (0.00%)
summary:                                 +      1 in     2s =    0.7/s Avg:  152                                                      7 Min:  1527 Max:  1527 Err:     0 (0.00%) Active: 2 Started: 2 Finished: 0

0: Write trades                               158 in    24s =    6.7/s Avg:   288 Min:   161 Max:   553 Err:     0 (0.00%)
1: Write trades_by_tickerday                  158 in  25.4s =    6.2/s Avg:    75 Min:     0 Max:   657 Err:     0 (0.00%)
3: Write trades_by_datehour                   158 in    25s =    6.4/s Avg:   163 Min:     1 Max:  1078 Err:     0 (0.00%)
4: Trade by id                                165 in  24.3s =    6.8/s Avg:   183 Min:     2 Max:   928 Err:     0 (0.00%)
5: 10 Minute Range By Ticker First 10         151 in    24s =    6.4/s Avg:   231 Min:     1 Max:  1163 Err:     0 (0.00%)
6: 10 Minute Count By Ticker                  157 in  26.3s =    6.0/s Avg:   228 Min:     1 Max:  1313 Err:     0 (0.00%)
7: 1 Minute Range By Hour First 10            182 in  24.3s =    7.5/s Avg:   295 Min:     1 Max:  1123 Err:     0 (0.00%)
summary:                                 +   1129 in  26.4s =   42.7/s Avg:   211 Min:     0 Max:  1313 Err:     0 (0.00%) Active: 17 arted: 17 Finished: 0
summary:                                 =   1130 in    27s =   42.0/s Avg:   212 Min:     0 Max:  1527 Err:     0 (0.00%)

0: Write trades                               309 in  30.5s =   10.1/s Avg:   353 Min:   161 Max:   785 Err:     0 (0.00%)
1: Write trades_by_tickerday                  310 in    30s =   10.3/s Avg:    67 Min:     0 Max:   210 Err:     0 (0.00%)
3: Write trades_by_datehour                   308 in    30s =   10.3/s Avg:   163 Min:     1 Max:   496 Err:     0 (0.00%)
4: Trade by id                                381 in    30s =   12.8/s Avg:   177 Min:     2 Max:   950 Err:     0 (0.00%)
5: 10 Minute Range By Ticker First 10         343 in    30s =   11.4/s Avg:   247 Min:     1 Max:  1132 Err:     0 (0.00%)
6: 10 Minute Count By Ticker                  352 in  30.2s =   11.7/s Avg:   266 Min:     1 Max:   891 Err:     0 (0.00%)
7: 1 Minute Range By Hour First 10            362 in  30.4s =   11.9/s Avg:   314 Min:     1 Max:  1178 Err:     0 (0.00%)
summary:                                 +   2365 in    31s =   77.5/s Avg:   228 Min:     0 Max:  1178 Err:     0 (0.00%) Active: 18 arted: 18 Finished: 0
summary:                                 =   3495 in    57s =   61.4/s Avg:   223 Min:     0 Max:  1527 Err:     0 (0.00%)

0: Write trades                               307 in  30.2s =   10.2/s Avg:   361 Min:   161 Max:   707 Err:     0 (0.00%)
1: Write trades_by_tickerday                  307 in    30s =   10.3/s Avg:    60 Min:     0 Max:   178 Err:     0 (0.00%)
3: Write trades_by_datehour                   307 in  30.1s =   10.2/s Avg:   162 Min:     1 Max:   498 Err:     0 (0.00%)
4: Trade by id                                330 in  30.3s =   10.9/s Avg:   185 Min:     2 Max:   801 Err:     0 (0.00%)
5: 10 Minute Range By Ticker First 10         349 in  30.1s =   11.6/s Avg:   270 Min:     1 Max:   706 Err:     0 (0.00%)
6: 10 Minute Count By Ticker                  337 in  30.3s =   11.1/s Avg:   251 Min:     2 Max:   969 Err:     0 (0.00%)
7: 1 Minute Range By Hour First 10            361 in    31s =   11.8/s Avg:   331 Min:     1 Max:  1440 Err:     0 (0.00%)
summary:                                 +   2298 in    31s =   75.0/s Avg:   234 Min:     0 Max:  1440 Err:     0 (0.00%) Active: 18 Started: 18 Finished: 0
summary:                                 =   5793 in    87s =   66.7/s Avg:   227 Min:     0 Max:  1527 Err:     0 (0.00%)

0: Write trades                               306 in  30.1s =   10.2/s Avg:   351 Min:   161 Max:   781 Err:     0 (0.00%)
1: Write trades_by_tickerday                  305 in    30s =   10.3/s Avg:    60 Min:     0 Max:   180 Err:     0 (0.00%)
3: Write trades_by_datehour                   307 in  30.2s =   10.2/s Avg:   175 Min:     1 Max:   480 Err:     0 (0.00%)
4: Trade by id                                350 in  30.2s =   11.6/s Avg:   167 Min:     2 Max:   953 Err:     0 (0.00%)
5: 10 Minute Range By Ticker First 10         341 in  30.4s =   11.2/s Avg:   257 Min:     1 Max:   852 Err:     0 (0.00%)
6: 10 Minute Count By Ticker                  339 in    30s =   11.4/s Avg:   273 Min:     1 Max:   868 Err:     0 (0.00%)
7: 1 Minute Range By Hour First 10            342 in  30.4s =   11.2/s Avg:   349 Min:     2 Max:  1330 Err:     0 (0.00%)
summary:                                 +   2290 in  30.5s =   75.1/s Avg:   235 Min:     0 Max:  1330 Err:     0 (0.00%) Active: 18 Started: 18 Finished: 0
summary:                                 =   8083 in   117s =   69.1/s Avg:   230 Min:     0 Max:  1527 Err:     0 (0.00%)
```

### Updated Commit log directory location
Moved the commitlog directory to a different disk to prevent I/O contention. Therfore updated the the directory in cassandra.yaml for all nodes from:
'''
commitlog_directory: "/mnt/ephemeral/cassandra/commitlog"
'''
to
'''
commitlog_directory: /var/lib/cassandra/commitlog
'''

### JVM Setting Update

When doing a run of the `top` command on some of the nodes in the cluster we noticed that Cassandra was using up 80-90% of the memory. Reviewing Cassandra-env.sh indicated that the MAX_HEAP_SIZE was set to 6GB on a 7GB system and HEAP_SIZE set to 4GB. These settings were high and did not leave much space for off-heap caching and OS caching. To fix this cassandra-env.sh was updated on all nodes and the cluster restarted

Exiting value in cassandra-env.sh
```sh
MAX_HEAP_SIZE=6G
HEAP_NEWSIZE=4G
```
New Value
```sh
MAX_HEAP_SIZE="2G"
HEAP_NEWSIZE="400M"
```

### Updated Read repair_chance
The table trades in stock keyspace has has read_repair chance of 1.0 and dclocal_read_repair_chance is set to 0.1. A read_repair_chance of 1 is quite high given that we will run repair on the cluster on weekly basis. As a result we update the read repair chance to 0.0.

Table DDL before the change
```sql
CREATE TABLE stock.trades (
    trade_id int PRIMARY KEY,
    company_name text,
    description text,
    exchng text,
    extra_id uuid,
    price float,
    quantity int,
    ticker text,
    trade_date timestamp,
    trade_timestamp timestamp
) WITH bloom_filter_fp_chance = 0.01
    AND caching = '{"keys":"ALL", "rows_per_partition":"NONE"}'
    AND comment = ''
    AND compaction = {'class': 'org.apache.cassandra.db.compaction.SizeTieredCompactionStrategy'}
    AND compression = {'sstable_compression': 'org.apache.cassandra.io.compress.LZ4Compressor'}
    AND dclocal_read_repair_chance = 0.1
    AND default_time_to_live = 0
    AND gc_grace_seconds = 864000
    AND max_index_interval = 2048
    AND memtable_flush_period_in_ms = 0
    AND min_index_interval = 128
    AND read_repair_chance = 1.0
    AND speculative_retry = '99.0PERCENTILE';
```
Change done to the read repair chance settings
```sh
cqlsh> ALTER TABLE stock.trades WITH read_repair_chance = 0.0;
```
    
