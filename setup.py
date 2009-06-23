from setuptools import setup, find_packages

setup(name="Electrickery",
      description="Port of OpenDSS to Python.",
      author="Richard W. Lincoln",
      author_email="r.w.lincoln@gmail.com",
      url="http://rwl.github.com/electrickery",
      version="0.1",
      license="Commercial",
      entry_points={"gui_scripts": ["electrickery = electrickery.main:main"]},
      install_requires=["CVXOPT"],
      include_package_data=True,
      packages=find_packages(),
      test_suite="electrickery.test",
      zip_safe=False)
