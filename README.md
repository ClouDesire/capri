# CAP Rest Api

Simple API for italian CAP lookup.

## Data source

Current database is obtained from https://github.com/matteocontrini/comuni-json

## API

### CAP Lookup

```
GET /cap=00195

Content-Type: application/json;charset=UTF-8

{
    "abbreviation": "RM", 
    "province": "Roma", 
    "region": "Lazio"
}
```

## Usage

An hosted instance is publicy available at [https://capra.cloudesire.com](https://capra.cloudesire.com/cap=00195), running on a Hetzner.de server.
