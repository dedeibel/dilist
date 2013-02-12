(ns omniplaylist.difm.parser
  (:require [net.cgrand.enlive-html :as enlive])
  (:require [clojure.string :as string])
  (:require [omniplaylist.difm.channel :as channel])
  (:require [omniplaylist.difm.stream  :as stream])
  (:use [omniplaylist.enlive-util]))

(def ^:dynamic *channel-selector* [:#head-content :ul.nav :ul.wide :> [:li (enlive/has [[:a (enlive/attr= :href "#")]])]])

(def ^:dynamic *channel-streams-container-selector* [:ul [:li (enlive/has [:li])]])

(def ^:dynamic *stream-quality-container-selector* [:ul :> :li :> :a])

(def ^:dynamic *stream-format-name-selector* [enlive/root :> :a])

(defn- filter-stream-name [stream-name-raw]
  (clojure.string/replace stream-name-raw #"\s*\n\s*" " "))

(defmulti channel-elements class)

(defmethod channel-elements java.lang.String
  [index-page]
    (select-string index-page *channel-selector*))

(defmethod channel-elements java.io.InputStream
  [index-page-stream]
    (-> index-page-stream (enlive/html-resource) (enlive/select *channel-selector*)))

(defn- title-element [channel-element]
  (-> channel-element (:content) (second)))

(defn- title-element-name [title-element]
  (-> title-element (:content) (first)))

(defn- streams [channel-element]
  (for [stream-container        (enlive/select channel-element *channel-streams-container-selector*)
        stream-quality-container (enlive/select stream-container *stream-quality-container-selector*)]
      (let [stream-format-name  (enlive/text (selectfirst stream-container *stream-format-name-selector*))
            stream-name        (filter-stream-name (enlive/text stream-quality-container))
            stream-uRL         (:href (:attrs stream-quality-container))]
        (stream/map->Stream 
          {
           :format stream-format-name
           :name   stream-name
           :url    stream-uRL
           }
          ))))

(defn extract-channel-data [channel-element]
  (channel/map->Channel
    {
     :name (title-element-name (title-element channel-element))
     :streams (streams channel-element)
     }))

(defn channels
  "Parses out all di.fm channels from a given di.fm index.html enlive parsed html page structure."
  [index-page]
            (map extract-channel-data (channel-elements index-page)))
