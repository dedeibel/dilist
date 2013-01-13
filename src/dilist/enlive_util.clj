(ns dilist.enlive-util
  (:require [net.cgrand.enlive-html :as enlive]))

(defmacro selectString
  [string & selects]
    `(-> ~string (java.io.StringReader.) (enlive/html-resource) (enlive/select ~@selects)))

(defmacro selectfirst [& params] `(first (enlive/select ~@params)))
