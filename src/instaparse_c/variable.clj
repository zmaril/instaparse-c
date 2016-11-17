(ns instaparse-c.variable
  (:refer-clojure :exclude [cat comment])
  (:require 
   [instaparse.combinators :refer :all]
   [instaparse-c.util :refer :all]))

(def variable 
  {:c11/variable (altnt :c11.variable/declaration :c11.variable/definition)
   :c11.variable/declaration
   (cat (opt (string "extern"))
        (opt (string "struct"))
        (altnt :c11/data-type :c11/symbol)
        (opt (string "*"))
        (list-of 
         (cat 
          (nt :c11/symbol)
          (opt (cat
                (string "[")
                (opt (nt :c11/expression))
                (string "]")))))
        (opt (hs ";")))
   :c11.variable/definition
   (cat (nt :c11.variable/declaration)
        (hs "=")
        (nt :c11/literal)
        (hs ";"))
   })