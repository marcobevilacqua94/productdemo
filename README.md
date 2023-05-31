# ingestor

This is a project meant to insert a big quantity of data in Couchbase using Java reactive api. It creates document using a data model you can explore and expand/change directly in the Java code.

use it with

```
docker run marcobevilacqua94/ingestor:latest java -jar ingestor.jar -h (host) -u (username) -p (password) -b (bucket-name) -s (scope-name) -c (collection-name) -b (buffer-size) -n (num-of-docs) -cl (content-limit) -pr (key-prefix) -st (start_key) -sh (shuflle) -shl (shuffle-length)
```

num of docs is the number of docs to upload.

content-limit is an option to tell the script to check the content of the collection to fill it to a certain size. It requires to run a select count query. The query is run each "buffer" number of insertion.

buffer-size is useful to tune if you encounter too many requests error. If you do not, make it as big as possible (even 1M).

key-prefix is something to append before the key of the document, which will be an incremental integer starting from start_key (or 0).

if you do not set the prefix but you want a random prefix use shuffle. Shuffle generates 3 random capitol letters in front of the sequential id. It stays the same during all the execution. If you want more letter, set it with shuffle-length.

default values for parameters are
```
host: 127.0.0.1
username: Administrator
password: password
bucker-name: sample
scope-name: _default
collection-name: _default
buffer-size: 1000
num-of-docs: 0 (infinite)
content-limit: 0 (infinite)
key-prefix: (empty)
start-key: 0
shuffle: false
shuffle-length: 3
```