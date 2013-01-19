(ns dilist.core
  (:require  [dilist.parser   :as parse])
  (:require  [dilist.download :as download])
  (:require  [dilist.channel  :as channel])
  (:require  [dilist.stream   :as stream])
  (:use  [clojure.pprint :only [pprint]]))

(defn -main []
    (let [allChannels (parse/channels (download/difm-page-as-stream))
          channels    (map channel/remove-premium allChannels)
;          playlists   (apply concat (map channel/extract-playlists channels))
          ]
      )
  )
