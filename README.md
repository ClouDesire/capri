# CAP Retrieve Interface

Simple REST API for italian CAP database lookup.

Semplice REST API per la ricerca all'interno del database dei CAP (codici di avviamento postale)

## Data source

Current database is obtained from [https://github.com/matteocontrini/comuni-json](https://github.com/matteocontrini/comuni-json)

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

## Usage

An hosted instance is publicly available at [https://capri.cloudesire.com](https://capri.cloudesire.com/cap/00195), running on a Hetzner.de server.
