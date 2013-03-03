(ns omniplaylist.difm.channel)

(defrecord Channel [name streams])

(defn remove-streams [channel filter-fn] 
  (assoc channel :streams (remove filter-fn (:streams channel))))

