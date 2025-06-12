# Zoológico 🦁

Sistema em Java para cadastrar e gerenciar animais e tratadores em um zoológico, seguindo arquitetura MVC com camadas `model`, `dao`, `controller` e `view`.

---

## 🧩 Estrutura do Projeto

- **model/**: classes que representam entidades (ex.: `Animal`, `Tratador`)  
- **dao/**: interfaces e implementações de acesso a dados (persistência)  
- **controller/**: regras de negócio e controle de fluxo  
- **view/**: interação com o usuário via terminal  
- **App.java**: ponto de entrada (`main`) que inicializa o sistema

---

## 🧠 Funcionalidades
Cadastrar, listar, atualizar e remover:

Animais

Tratadores

Validações básicas (ex.: campos obrigatórios)

Interface via terminal para navegação simples

## 💾 Persistência
Atualmente, os dados não são salvos em banco externo. A persistência é feita em memória (ou arquivos, conforme implementação das classes DAO).
