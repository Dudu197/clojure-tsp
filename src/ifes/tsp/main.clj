(ns ifes.tsp.main
  (:require [ifes.tsp.tsp-core :as tsp-core]
            [ifes.tsp.brute_force :as brute-force]
            [ifes.tsp.greedy :as greedy]
            [jsonista.core :as json]
            [clojure.core.match :refer [match]]
            )
  (:gen-class))

(defn imprimir-mapa
  [mapa]
  (println "Mapa gerado:")
  (doseq [i (sort (keys mapa))] (println i ":" (get mapa i)))
)

(defn executar-forca-bruta
  [mapa]
  (println "Executando algoritmo força bruta")
  (def start-time (System/currentTimeMillis))
  (println (brute-force/melhor-rota mapa (brute-force/gerar-rotas)))
  (def end-time (System/currentTimeMillis))
  (println "Força bruta executado em:" (- end-time start-time) "ms")
)

(defn executar-guloso
  [mapa]
  (println "Executando algoritmo guloso")
  (def start-time (System/currentTimeMillis))
  (println (greedy/melhor-rota mapa))
  (def end-time (System/currentTimeMillis))
  (println "Algoritmo guloso executado em:" (- end-time start-time) "ms")
)

(defn imprimir-instrucoes []
  (println "Nenhum argumento foi informado")
  (println "Para executar, utilize os seguintes parâmetros em sequência:")
  (println "Algoritmo (obrigatório): informe qual algoritmo será executado. Opções: bruteforce, greedy, both")
  (println "Mapa (opcional): informe um arquivo json para ser utilizado como mapa. Incompatível se o número de cidades for fornecido")
  (println "Cidades (opcional): informe o número de cidades a ser gerado. Incompatível se o mapa for fornecido")
  (println "Distância máxima (opcional): informe a distância máxima entre as cidades. Incompatível se o mapa for fornecido. Obrigatório de o número de cidades for fornecido")
  (println "Número de conexões (opcional): informe o número mínimo de conexões que cada cidade deve ter. Deve ser menor que o número de cidades. Incompatível se o mapa for fornecido. Obrigatório de o número de cidades for fornecido")
  (println "")
  (println "Exemplos:")
  (println "lein run both resources/ex01.json")
  (println "lein run greedy 10 50 9")
)

(defn executar-algoritmo
  [algoritmo mapa]
  (imprimir-mapa mapa)
  (match algoritmo
     "bruteforce" (executar-forca-bruta mapa)
     "greedy" (executar-guloso mapa)
     "both" [(executar-forca-bruta mapa) (executar-guloso mapa)]
  )
)

(defn executar-json
  [algoritmo arquivo]
  (executar-algoritmo algoritmo (json/read-value (slurp arquivo)))
)

(defn executar-parametros
  [algoritmo cidades distancia conexoes]
  (tsp-core/definir-num-cidades cidades)
  (tsp-core/definir-distancia-maxima distancia)
  (tsp-core/definir-num-conexoes conexoes)
  (executar-algoritmo algoritmo (tsp-core/gerar-cidades-seguro))
)

(defn -main
  [& args]
  (match (count args)
     0 (imprimir-instrucoes)
     2 (executar-json (nth args 0) (nth args 1))
     4 (executar-parametros (nth args 0) (Integer/parseInt (nth args 1)) (Integer/parseInt (nth args 2)) (Integer/parseInt (nth args 3)))
  )
)
