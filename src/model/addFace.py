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

try:
    with open("src/model/serializeStorage.txt", "r") as file:
        people_dict = json.load(file)
        file.close()
except:
    print("Empty serialized storage file")
    pass

# print(people_dict)
image_id = len(people_dict)

destination = os.getcwd() + "/src/model/images"
copy(addFacePath, destination)
# print(destination + "/" + addFacePath.split("/")[-1])

# people.addPerson(Person(person_name, destination + "/" + addFacePath.split("/")[-1]))

people_dict[image_id] = [person_name, "src/model/images/" + addFacePath.split("/")[-1]]

with open("src/model/serializeStorage.txt", "w") as file:
    json.dump(people_dict, file)
    file.close()

print("success")
