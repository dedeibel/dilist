(ns dilist.channel
  (:require [dilist.filter :as filter]))

(defrecord Channel [name streams])

(defn remove-streams [filter-fn channel] 
  (assoc channel :streams (remove filter-fn (:streams channel))))

(def remove-premium #(remove-streams filter/is-premium %))
