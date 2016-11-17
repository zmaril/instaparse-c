(ns instaparse-c.for
  (:refer-clojure :exclude [cat comment function for])
  (:require 
   [instaparse.combinators :refer :all]
   [instaparse-c.util :refer :all]))

(def for 
  {:c11/for
   (cat
    (hs "for")
    (parens
     (cat (nt :c11/expression)
          (string ";")
          (nt :c11/expression)
          (string ";")
          (nt :c11/expression)))
    (alt (nt :c11/statement)
         (cat (hs "{")
              (nt :c11)
              (hs "}")))
    )
   })