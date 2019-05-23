(ns easy-crawler.core
  (:require [itsy.core :refer :all]
            [itsy.handlers.textfiles :refer :all]))

(defn my-handler [{:keys [url body]}]
  (println url "has a count of" (count body)))

(defn cf []
  (crawl {;; initial URL to start crawling at (required)
               :url "http://aoeu.com"
               ;; handler to use for each page crawled (required)
               :handler my-handler
               ;; number of threads to use for crawling, (optional,
               ;; defaults to 5)
               :workers 10
               ;; number of urls to spider before crawling stops, note
               ;; that workers must still be stopped after crawling
               ;; stops. May be set to -1 to specify no limit.
               ;; (optional, defaults to 100)
               :url-limit 100
               ;; function to use to extract urls from a page, a
               ;; function that takes one argument, the body of a page.
               ;; (optional, defaults to itsy's extract-all)
               :url-extractor extract-all
               ;; http options for clj-http, (optional, defaults to
               ;; {:socket-timeout 10000 :conn-timeout 10000 :insecure? true})
               :http-opts {}
               ;; specifies whether to limit crawling to a single
               ;; domain. If false, does not limit domain, if true,
               ;; limits to the same domain as the original :url, if set
               ;; to a string, limits crawling to the hostname of the
               ;; given url
               :host-limit false
               ;; polite crawlers obey robots.txt directives
               ;; by default this crawler is polite
               :polite? true}))

(def ^:dynamic c true)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!")
  (binding [c c]
    (set! c cf)))

;;; in REPL
;; (def zzz (cf))
;; zzz
