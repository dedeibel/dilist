(ns dilist.core
  (:require [net.cgrand.enlive-html :as enlive])
  (:require [clojure.string :as string]))

(defmacro selectString
  [string & selects]
    `(-> ~string (java.io.StringReader.) (enlive/html-resource) (enlive/select ~@selects)))

(defn- channelElements
  [indexPage]
    (selectString indexPage
      [:#head-content :ul.nav :ul.wide :> [:li (enlive/has [[:a (enlive/attr= :href "#")]])]]))

(defn- titleElement [channelElement]
  (-> channelElement (:content) (second)))

(defn- titleElementName [titleElement]
  (-> titleElement (:content) (first)))

(defn- streams [channelElement]
  (for [streamContainer        (enlive/select channelElement [:ul [:li (enlive/has [:li])]])
        streamQualityContainer (enlive/select streamContainer [:ul :> :li :> :a])]
    (let [streamFormatName  (-> streamContainer (enlive/select [enlive/root :> :a]) (first) (:content) (first))
          streamNameRaw     (-> streamQualityContainer (enlive/select [enlive/text-node]))
          streamName        (string/replace streamNameRaw #"\n" "")
          streamURL         (:href (:attrs streamQualityContainer))]
      {
       :format streamFormatName
       :name   streamName ; TODO WHY IS this "lazyseq" :-\
       :urt    streamURL
       })))

; TODO build element extraction recursively, each element in it's own method - input the html element, output
; datastructure with all stuff on the way up
(defn extractChannel [channelElement]
  {
   :name (titleElementName (titleElement channelElement))
   :streams (streams channelElement)
   })

(defn channels
  "Parses out all di.fm channels from a given di.fm index.html enlive parsed html page structure."
  [indexPage]
    (conj
      (drop 1
            (map extractChannel (channelElements indexPage)))
      {
       :name "Deep Tech"
       :streams [
                 {
                  :url "http://listen.di.fm/public3/deeptech.pls"
                  }
                 ]
       }
      ))
