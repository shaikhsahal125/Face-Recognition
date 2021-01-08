# from people import People
# from person import Person
from shutil import copy

import json
import sys
import os

person_name = sys.argv[1]
addFacePath = sys.argv[2]

# people = People()

print(os.getcwd())
destination = os.getcwd() + "/src/model/images"
copy(addFacePath, destination)
# print(destination + "/" + addFacePath.split("/")[-1])

# people.addPerson(Person(person_name, destination + "/" + addFacePath.split("/")[-1]))

print("success")
