(ns omniplaylist.difm.stream_test
  (:require name.benjaminpeter.clj-pls)
  (:use omniplaylist.difm.stream)
  (:use clojure.test)
  (:use midje.sweet))

(fact "A premium stream is recognized."
      (is-premium (map->Stream {:format "MP3" :name "128k Broadband (Premium)" :url "/premium"})) => true)

(fact "A normal stream is not recognized as premium."
      (is-premium (map->Stream {:format "MP3" :name "128k Broadband" :url "http://listen.di.fm/public2/chiptunes.pls"})) => false)

(fact "A playlist with no tracks must be returned in case of an empty stream url."
      (-> (map->Stream {:format "MP3" :name "128k Broadband" :url nil}) download-and-parse-playlist :tracks)
      => (every-pred coll? empty?))

(def playlist-test-structure
  {:entries 3,
   :version "2",
   :files
   [{:url "http://pub4.di.fm:80/di_chiptunes_aacplus",
     :title "Digitally Imported - Chiptunes",
     :length -1}
    {:url "http://pub1.di.fm:80/di_chiptunes_aacplus",
     :title "Digitally Imported - Chiptunes",
     :length -1}
    {:url "http://pub2.di.fm:80/di_chiptunes_aacplus",
     :length -1,
     :title "Digitally Imported - Chiptunes"}]})


(def test-stream (map->Stream
                   {
                    :format "MP3"
                    :name "128k Broadband" 
                    :url "http://example.com/public2/chiptunes.pls"}))

(fact "A playlist with tracks must be returned"
      (:tracks (download-and-parse-playlist test-stream)) => (contains {
                                                                        :title "Digitally Imported - Chiptunes"
                                                                        :url "http://pub4.di.fm:80/di_chiptunes_aacplus"
                                                                        :format nil
                                                                        :length -1}
                                                                       {
                                                                        :title "Digitally Imported - Chiptunes"
                                                                        :url "http://pub1.di.fm:80/di_chiptunes_aacplus"
                                                                        :format nil
                                                                        :length -1})

      (provided (name.benjaminpeter.clj-pls/parse anything) => playlist-test-structure)
      (count (:tracks (download-and-parse-playlist test-stream))) => 3
      (provided (name.benjaminpeter.clj-pls/parse anything) => playlist-test-structure))

