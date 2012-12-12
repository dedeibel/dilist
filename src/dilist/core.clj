(ns dilist.core
  (:require [net.cgrand.enlive-html :as enlive]))

(defmacro selectString
  [string & selects]
    `(-> ~string (java.io.StringReader.) (enlive/html-resource) (enlive/select ~@selects)))


(defn- channelElements
  [indexPage]
    (selectString indexPage
      [:#head-content :ul.nav :ul.wide :> [:li (enlive/has [[:a (enlive/attr= :href "#")]])]]))

(defn channels
  "Parses out all di.fm channels from a given di.fm index.html enlive parsed html page structure."
  [indexPage]
  (for [channel (channelElements indexPage)]
    {:name (first (:content (second (:content channel))))})
)
