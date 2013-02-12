(ns omniplaylist.core
  (:require  [omniplaylist.download      :as download])
  (:require  [omniplaylist.difm.parser   :as parse])
  (:require  [omniplaylist.difm.channel  :as channel])
  (:require  [omniplaylist.difm.download :as difm])
  (:use  [clojure.pprint :only [pprint]]))

(defn -main []
    (let [allChannels (parse/channels (difm/difm-page-as-stream))
          channels    (map channel/remove-premium allChannels)
;          playlists   (apply concat (map channel/extract-playlists channels))
          ]
      (pprint channels)
      )
  )
