(ns dilist.parser
  (:require [net.cgrand.enlive-html :as enlive])
  (:require [clojure.string :as string])
  (:use [dilist.enlive-util]))

(def ^:dynamic *channelSelector* [:#head-content :ul.nav :ul.wide :> [:li (enlive/has [[:a (enlive/attr= :href "#")]])]])

(def ^:dynamic *channelStreamsContainerSelector* [:ul [:li (enlive/has [:li])]])

(def ^:dynamic *streamQualityContainerSelector* [:ul :> :li :> :a])

(def ^:dynamic *streamFormatNameSelector* [enlive/root :> :a])

(defn- filterStreamName [streamNameRaw]
  (clojure.string/replace streamNameRaw #"\s*\n\s*" " "))

(defmulti channelElements class)

(defmethod channelElements java.lang.String
  [indexPage]
    (selectString indexPage *channelSelector*))

(defmethod channelElements java.io.InputStream
  [indexPageStream]
    (-> indexPageStream (enlive/html-resource) (enlive/select *channelSelector*)))

(defn- titleElement [channelElement]
  (-> channelElement (:content) (second)))

(defn- titleElementName [titleElement]
  (-> titleElement (:content) (first)))

(defn- streams [channelElement]
  (for [streamContainer        (enlive/select channelElement *channelStreamsContainerSelector*)
        streamQualityContainer (enlive/select streamContainer *streamQualityContainerSelector*)]
      (let [streamFormatName  (enlive/text (selectfirst streamContainer *streamFormatNameSelector*))
            streamName        (filterStreamName (enlive/text streamQualityContainer))
            streamURL         (:href (:attrs streamQualityContainer))]
        {
         :format streamFormatName
         :name   streamName
         :url    streamURL
         })))

(defn extractChannelData [channelElement]
  {
   :name (titleElementName (titleElement channelElement))
   :streams (streams channelElement)
   })

(defn channels
  "Parses out all di.fm channels from a given di.fm index.html enlive parsed html page structure."
  [indexPage]
            (map extractChannelData (channelElements indexPage)))
