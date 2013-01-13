(ns dilist.core
  (:require  [dilist.parser   :as parse])
  (:require  [dilist.download :as download])
  (:require  [dilist.channel  :as channel])
  (:require  [dilist.stream   :as stream])
  (:require  [dilist.filter   :as filter])
  (:use  [clojure.pprint :only [pprint]]))

(defn -main []
  (->> (download/difm-page-as-stream) parse/channels (map channel/remove-premium) pprint)
  )
