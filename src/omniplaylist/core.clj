(ns omniplaylist.core
  (:gen-class)
  (:require  [name.benjaminpeter.clj-pls  :as pls])
  (:require  [omniplaylist.difm.page      :as difm-page])
  (:use  [clojure.pprint :only [pprint]]))

(defn -main []
  (let [difm-playlists (difm-page/all-streams-as-playlist) ]
    (pls/write! *out* { :files difm-playlists })) (shutdown-agents))
