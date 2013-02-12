(ns omniplaylist.difm.download
  (:require [omniplaylist.download :as download]))

(def ^:dynamic *digitally-imported-URL* "http://di.fm")

(defn difm-page-as-stream [] 
  (download/as-stream *digitally-imported-URL*))
