(ns dilist.filter)

(defmulti isPremium class)

(defn noPremium [stream] (remove isPremium stream))

