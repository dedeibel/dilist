(ns omniplaylist.difm.page
  (:require [omniplaylist.download :as download]))

(def ^:dynamic *digitally-imported-URL* "http://di.fm")

(defn difm-page-as-stream [] 
  (download/as-stream *digitally-imported-URL*))

; TODO: get all tracks from the channels as one list and prepend the channel's names


