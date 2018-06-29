(defproject tradeshift-task "0.0.0"
  :description "Tradeshift task"
  :url "https://github.com/flexiana/tradeshift-task"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.9.0"]

                 ;; Duct
                 [duct/core "0.6.2"]
                 [duct/module.logging "0.3.1"]
                 [duct/module.web "0.6.4"]
                 [duct.module.bidi "0.5.0"]

                 ;; HTTP
                 [ring/ring-json "0.4.0"]

                 ;; Utils
                 [funcool/struct "1.3.0"]
                 [hiccup "1.0.5"]]

  :plugins [[duct/lein-duct "0.10.6"]]
  :main ^:skip-aot tradeshift-task.main
  :uberjar-name "application.jar"
  :uberjar {:aot :all}
  :resource-paths ["resources" "target/resources"]
  :prep-tasks ["javac" "compile" ["run" ":duct/compiler"]]
  :source-paths ["src/clj" "src/cljc"]
  :eastwood {:source-paths ["src/clj" "src/cljc"]}
  :profiles
  {:dev [:project/dev :profiles/dev]
   :repl {:prep-tasks ^:replace ["javac" "compile"]
          :repl-options {:init-ns user}}
   :uberjar {:aot :all}
   :profiles/dev {}
   :project/dev  {:source-paths ["dev/src"]
                  :resource-paths ["dev/resources"]
                  :dependencies [[integrant/repl "0.3.1"]
                                 [eftest "0.5.2"]]
                  :plugins [[lein-cloverage "1.0.10"]
                            [jonase/eastwood "0.2.5"]
                            [lein-kibit "0.1.6"]
                            [venantius/ultra "0.5.2"]
                            [lein-ancient "0.6.15"]]}}
  :aliases {"coverage" ["cloverage"
                        "--fail-threshold" "90"
                        "-e" "dev|user|local|tradeshift-task.main"]})
