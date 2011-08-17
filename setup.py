from os.path import abspath, dirname, join
from setuptools import setup

cwd = abspath(dirname(__file__))
f = open(join(cwd, "README"))
kwds = {"long_description": f.read()}
f.close()

setup(name="pydss",
      description="Python Distribution System Simuator",
      author="Richard Lincoln",
      author_email="r.w.lincoln@gmail.com",
      version="0.1a",
      license="Apache License 2.0",
      packages=['pydss',
                'pydss.common',
                'pydss.control',
                'pydss.conversion',
                'pydss.delivery',
                'pydss.executive',
                'pydss.general',
                'pydss.meter',
                'pydss.parser',
                'pydss.plot',
                'pydss.shared'],
      zip_safe=True,
      **kwds)
