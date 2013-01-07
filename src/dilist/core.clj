(ns dilist.core
  (:require  [dilist.parser :as parser])
  (:require  [dilist.download :as download])
  (:use  [clojure.pprint :only [pprint]]))

(defn -main []
  (pprint (parser/channels (download/difmPageAsStream)))
  )
