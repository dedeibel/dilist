(ns omniplaylist.difm.channel
  (require [omniplaylist.difm.stream :as stream]))

(defrecord Channel [name streams])

(defn remove-streams [filter-fn channel] 
  (assoc channel :streams (remove filter-fn (:streams channel))))

(def remove-premium #(remove-streams stream/is-premium %))
