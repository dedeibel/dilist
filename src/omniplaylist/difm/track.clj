(ns omniplaylist.difm.track
  (:require [omniplaylist.track :as track]))

(defn shorten-digitally-imported-name [track]
  (track/update-title track #(clojure.string/replace % #"Digitally Imported" "di.fm"))) 
