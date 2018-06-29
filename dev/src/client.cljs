(ns dev.client
  (:require
    [figwheel.client :as fw]
    [tradeshift-task.main]))

(enable-console-print!)


(fw/start {:on-jsload tradeshift-task.main/init
           :websocket-url "ws://localhost:3449/figwheel-ws"})
