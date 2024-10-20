# Python

---

The ```Python``` learning resources.


## Project Setup

* Configuration
* Dependencies
* Database Configuration
* Build Instructions
* Unit Tests
* Deployment Instructions

### Python Settings
```shell
python3 --version
python3 -m pip --version
python3 -m ensurepip --default-pip
```

### Setup Virtual Environment
```
python3 -m pip install virtualenv
python3 -m venv venv
source deactivate
source venv/bin/activate
```

### Upgrade PIP Requirements (Dependencies)
```shell
pip install --upgrade pip
```

### Setup Configurations

- Create or update local .env configuration file.

```shell
touch .env
HOST = 127.0.0.1
PORT = 8080
DEBUG = True
DEFAULT_POOL_SIZE = 1
RDS_POOL_SIZE = 1
```

**By default**, Flask will run the application on **port 5000**.

### Build Service
```shell
python3 -m build
```

### Save Requirements (Dependencies)
```shell
pip freeze > requirements.txt
```

## Unit Tests
```shell
python3 -m unittest
python -m unittest discover -s ./tests -p "test_*.py"
```

# Reference

- [Gunicorn](https://flask.palletsprojects.com/en/3.0.x/deploying/gunicorn/)
- [Gunicorn - WSGI server](https://docs.gunicorn.org/en/latest/index.html)
- [wsgiref — WSGI Utilities and Reference Implementation](https://docs.python.org/3/library/wsgiref.html)
- [Python Packaging User Guide](https://packaging.python.org/en/latest/)
- [Flask](https://flask.palletsprojects.com/en/3.0.x/)
- [The best Python HTTP clients](https://www.scrapingbee.com/blog/best-python-http-clients/)
- [Web Crawler in Python: Step-by-Step Tutorial](https://www.zenrows.com/blog/web-crawler-python)
- [Python Paste](https://pythonpaste.readthedocs.io/en/latest/index.html)
- [WSGI Servers](https://www.fullstackpython.com/wsgi-servers.html)
- [WSGI: The Server-Application Interface for Python](https://www.toptal.com/python/pythons-wsgi-server-application-interface)
- [Python FastAPI vs Flask: A Detailed Comparison](https://www.turing.com/kb/fastapi-vs-flask-a-detailed-comparison)
- [The Zen of Python](https://peps.python.org/pep-0020/#id3)
```text
- Beautiful is better than ugly.
- Explicit is better than implicit.
- Simple is better than complex.
- Complex is better than complicated.
- Flat is better than nested.
- Sparse is better than dense.
- Readability counts.
- Special cases aren't special enough to break the rules.
- Although practicality beats purity.
- Errors should never pass silently.
- Unless explicitly silenced.
- In the face of ambiguity, refuse the temptation to guess.
- There should be one-- and preferably only one --obvious way to do it.
- Although that way may not be obvious at first unless you're Dutch.
- Now is better than never.
- Although never is often better than *right* now.
- If the implementation is hard to explain, it's a bad idea.
- If the implementation is easy to explain, it may be a good idea.
- Namespaces are one honking great idea -- let's do more of those!
```
- 

## Contribution Guidelines

* Writing tests
* Code review
* Other Guidelines

## Version

```text
MAJOR.MINOR.PATCH.BUILD
```

# Author

- Rohtash Lakra
