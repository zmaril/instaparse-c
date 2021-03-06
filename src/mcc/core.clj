(ns mcc.core
  (:refer-clojure :exclude [cat comment struct do printf])
  (:require
   [clojure.string :refer [split-lines trim join]]
   [clojure.spec :as s]
   [mcc.parse :refer [clean-parse]]
   [mcc.bundle :refer [into-bundles]]
   [mcc.chunk  :refer [into-chunks]]
   [mcc.produce :refer [produce-text produce-strings]]
   [mcc.datascript  :refer [enlive-output->datascript-datums schema]]
   [mcc.util :refer [altnt cart]]
   [clojure.data :refer [diff]]
   [com.rpl.specter :refer :all]
   [datascript.core :as d]))

(defn string->db [s]
  (let [chunked (-> s into-bundles into-chunks)
        lines   (first (produce-strings chunked))
        parsed  (clean-parse)
        datums  (enlive-output->datascript-datums parsed)
        db      (d/create-conn schema)]
       (d/transact! db [datums])
       db))

;;Dev code
(def sample (slurp  "/home/zmaril/software/postgres/src/backend/access/transam/xlog.c"))
#(def sample (slurp  "dev-resources/corpus/openssh-portable/auth.c"))
#(def db (string->db sample))

#_(d/q '[:find  ?prize]
       :where [?value :value "e"]
              [?symbol :content ?value]
              [?fun-call :content ?symbol]
              [?fun-call :content ?args]
              [?args :order 1]
              [?args :content ?temp1]
              [?temp1 :content ?temp2]
              [?temp2 :value ?prize]
     @db)

#_(d/q '[:find  ?fun-call]
       :where [?value :value "printf"]
              [?symbol :content ?value]
              [?fun-call :content ?symbol]
     @db)
(defn sampled [s]
  (let [chunked (-> s into-bundles into-chunks)
        parsed  (map clean-parse (produce-strings chunked))
        datums  (map enlive-output->datascript-datums parsed)]
   chunked))

#_(let [{:keys [:instaparse.gll/start-line]}]
      (meta (nth (sampled sample) 1))
     start-line)

#_(-> sample into-bundles into-chunks first)
#_(def static (->> sample into-bundles into-chunks rest first))

(->> sample into-bundles into-chunks (map produce-text))
