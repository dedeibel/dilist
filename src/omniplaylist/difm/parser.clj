(ns omniplaylist.difm.parser
  (:require [net.cgrand.enlive-html    :as enlive]
            [omniplaylist.difm.channel :as channel]
            [omniplaylist.playlist-url :as playlist-url])
  (:use [omniplaylist.enlive-util]))

(def ^:dynamic *channel-selector* [:.main :div.channels :div.lists :> :ul :> :li :> :a])

; Whole URL will be (str *playlist-base-url* CHANNEL_PATH ".pls")
(def ^:dynamic *playlist-base-url* "http://listen.di.fm/public2/")

(defmulti channel-elements class)

(defmethod channel-elements java.lang.String
  [index-page]
  (select-string index-page *channel-selector*))

(defmethod channel-elements java.io.InputStream [index-page-stream]
  (-> index-page-stream (enlive/html-resource) (enlive/select *channel-selector*)))

(defn- title [channel-element]
  (-> channel-element :content (nth 3) :content first))

(defn- path [channel-element]
  (subs (-> channel-element :attrs :href) 1))

(defn- url [path]
  (str *playlist-base-url* path ".pls"))

(defn- streams [channel-element]
  (vector (let [title        (title channel-element)
        path-name    (path channel-element)
        pls-url      (url path-name)]
    (playlist-url/map->PlaylistURL 
      {
       :format nil
       :name   title
       :url    pls-url
       }
      ))))

(defn extract-channel-data [channel-element]
  (channel/map->Channel
    {
     :name    (title channel-element)
     :streams (streams channel-element)
     }))

(defn parse-all-channels-playlists
  "Parses out all di.fm channels from a given di.fm index.html enlive parsed html page structure."
  [index-page]
  (map extract-channel-data (channel-elements index-page)))

