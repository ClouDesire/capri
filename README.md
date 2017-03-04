# CAP Retrieve Interface [![CircleCI](https://circleci.com/gh/ClouDesire/capri.svg?style=svg)](https://circleci.com/gh/ClouDesire/capri) [![Docker Pulls](https://img.shields.io/docker/pulls/cloudesire/capri.svg?style=plastic)](https://hub.docker.com/r/cloudesire/capri/)

Simple REST API for italian CAP database lookup.

Semplice REST API per la ricerca all'interno del database dei CAP (codici di avviamento postale)

## Data source

Current database is obtained from [https://github.com/matteocontrini/comuni-json](https://github.com/matteocontrini/comuni-json)

## Install

Install with docker

```sh
$ docker run -e 'SERVER_PORT=8080'-p 8080:8080 -d cloudesire/capri
```

webservice is now exposed on port 8080 (default port).

## API

### CAP Lookup

```
GET /cap/00195

Content-Type: application/json;charset=UTF-8

{
    "abbreviation": "RM", 
    "cities": [
        "Roma"
    ], 
    "province": "Roma", 
    "region": "Lazio"
}

```

## Public best-effort service

An hosted instance is publicly available at [https://capri.cloudesire.com](https://capri.cloudesire.com/cap/00195), running on a Hetzner.de server.
