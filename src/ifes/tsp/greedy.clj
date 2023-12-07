(ns ifes.tsp.greedy
  (:require [ifes.tsp.tsp-core :as tsp-core])
  (:gen-class)
)

(defn proximas-rotas
  "Calcula as rotas para uma lista de cidades"
  [mapa cidade-atual cidades-restantes]
  (map
    (fn [proxima-cidade] {:proxima-cidade proxima-cidade :custo (tsp-core/distancia mapa cidade-atual proxima-cidade)})
    cidades-restantes
  )
)

(defn melhor-cidade
  "Retorna a cidade com o menor custo"
  [rotas]
  (apply min-key :custo rotas)
)

(def primeira-rota "Calcula a primeira rota com base em uma cidade aleatória" [(inc (rand-int (dec tsp-core/num-cidades)))])

(defn remover-cidade
  "Remove uma cidade da lista"
  [cidade cidades]
  (remove #(= cidade %) cidades)
)


(def cidades "Cria uma lista das cidades" (remover-cidade (first primeira-rota) (range 1 (inc tsp-core/num-cidades))))

(defn proxima-melhor-cidade
  "Escolhe a cidade com menor custo para seguir"
  [mapa rota cidades-restantes]
  (get (melhor-cidade (tsp-core/rotas-validas(proximas-rotas mapa (last rota) cidades-restantes))) :proxima-cidade)
)

(defn melhor-rota
  "Calcula a rota utilizando algoritmo guloso, escolhendo sempre a próxima rota de menor custo"
  [mapa rota cidades-restantes]
  (if (> (count cidades-restantes) 1)
    (recur mapa (conj rota (proxima-melhor-cidade mapa rota cidades-restantes)) (remover-cidade (proxima-melhor-cidade mapa rota cidades-restantes) cidades-restantes))
    (conj rota (first cidades-restantes))
  )
)
