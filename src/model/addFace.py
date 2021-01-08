# from people import People
# from person import Person
from shutil import copy

import json
import sys
import os

person_name = sys.argv[1]
addFacePath = sys.argv[2]

# people = People()
people_dict = {}

with open("src/model/serializeStorage.txt", "r") as file:
    people_dict = json.load(file)
    file.close()

print(people_dict)

print(os.getcwd())
destination = os.getcwd() + "/src/model/images"
copy(addFacePath, destination)
# print(destination + "/" + addFacePath.split("/")[-1])

# people.addPerson(Person(person_name, destination + "/" + addFacePath.split("/")[-1]))

people_dict[person_name] = destination

with open("src/model/serializeStorage.txt", "w") as file:
    json.dump(people_dict, file)
    file.close()

print("success")
