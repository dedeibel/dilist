(ns omniplaylist.playlist-url-test
  (:use midje.sweet
        omniplaylist.playlist-url)
  (:require [name.benjaminpeter.clj-pls]
            [omniplaylist.download :as download]))

(fact "A playlist with no tracks must be returned in case of an empty stream url."
      (-> (map->PlaylistURL {:format "MP3" :name "128k Broadband" :url nil}) download-and-parse-playlist :tracks)
      => (every-pred coll? empty?))

(def download-and-parse-playlist-test-download-mock-result
  {:entries 3,
   :version "2",
   :files
   [{:url "http://pub4.di.fm:80/di_chiptunes_aacplus",
     :title "Digitally Imported - Chiptunes",
     :length -1}
    {:url "http://pub1.di.fm:80/di_chiptunes_aacplus",
     :title "Digitally Imported - Chiptunes",
     :length -1}
    ]})


(def test-playlist (map->PlaylistURL
                   {
                    :format "MP3"
                    :name "128k Broadband" 
                    :url "http://example.com/public2/chiptunes.pls"}))

(def download-and-parse-playlist-expected-tracks '({
                                                    :title "Digitally Imported - Chiptunes"
                                                    :url "http://pub4.di.fm:80/di_chiptunes_aacplus"
                                                    :format "MP3"
                                                    :length -1},
                                                   {
                                                    :title "Digitally Imported - Chiptunes"
                                                    :url "http://pub1.di.fm:80/di_chiptunes_aacplus"
                                                    :format "MP3"
                                                    :length -1}))

(fact "A playlist containing the number of tracks as specified in the playlist file must be returned"
      (count (:tracks (download-and-parse-playlist test-playlist))) => (count download-and-parse-playlist-expected-tracks)
      (provided
        (name.benjaminpeter.clj-pls/parse anything) => download-and-parse-playlist-test-download-mock-result
        (download/as-stream anything)  => nil))

(fact "The title of the tracks is set according the playlist information"
      (map :title (:tracks (download-and-parse-playlist test-playlist))) => (map :title download-and-parse-playlist-expected-tracks)
      (provided
        (name.benjaminpeter.clj-pls/parse anything) => download-and-parse-playlist-test-download-mock-result
        (download/as-stream anything)  => nil
        ))

(fact "The format of the tracks is set to the playlist's format"
      (map :format (:tracks (download-and-parse-playlist test-playlist))) => (n-of (:format test-playlist) (count download-and-parse-playlist-expected-tracks))
      (provided
        (name.benjaminpeter.clj-pls/parse anything) => download-and-parse-playlist-test-download-mock-result
        (download/as-stream anything)  => nil
        ))

(fact "The playlist has the name of the playlist url"
      (:name (download-and-parse-playlist test-playlist)) => (:name test-playlist)
      (provided
        (name.benjaminpeter.clj-pls/parse anything) => download-and-parse-playlist-test-download-mock-result
        (download/as-stream anything)  => nil
        ))

