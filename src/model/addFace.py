from people import People
from person import Person
from shutil import copy

import json
import sys
import os

person_name = sys.argv[1]
addFacePath = sys.argv[2]

people = People()

print(os.getcwd())
destination = os.getcwd() + "/src/model/images"
copy(addFacePath, destination)
# print(destination + "/" + addFacePath.split("/")[-1])

people.addPerson(Person(person_name, destination + "/" + addFacePath.split("/")[-1]))

# file = open("serializeStorage.txt", "w")
#
# if not file:
#     print("not file")
# else:
#     print("file opened")
#
# print(json.dumps(people, default=vars))
print(people)

with open("serializeStorage.txt", "wb") as file:
    file.write(json.dumps(people, default=vars))


with open("serializeStorage.txt", "rb") as f:
    p = json.load(f)
    f.close()

print(p)
print("success")
file.close()
