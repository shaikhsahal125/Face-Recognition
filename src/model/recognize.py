# from people import People
# from person import Person

import PIL.Image
import PIL.ImageDraw
import json
import face_recognition as fc
import sys

# ppl = People()
# ppl.addPerson(Person("Sahal", "/die/dke"))
# print(ppl)
# print(ppl.getPeople()[0].getName())

face_input = sys.argv[1]

print(face_input)

# if (" " in face_input):
#     face_input.replace("\ ", " ")
#
# print(face_input)

# load image path as an image
image = fc.load_image_file(face_input)

# find faces
face_locations = fc.face_locations(image)
print(f"{len(face_locations)} faces were found")

print(face_locations)

# encodings
img_encoding = fc.face_encodings(image)
print(img_encoding)

# get saved faces from JSON file
with open("src/model/serializeStorage.txt", "r") as jsonFile:
    facesDict = json.load(jsonFile)
    jsonFile.close()

print(facesDict)

best_dist = 1.0
best_match = None


# convert array image to PIL images
pil_img = PIL.Image.fromarray(image)
draw = PIL.ImageDraw.Draw(pil_img)
for fl in face_locations:
    print(fl)
    t, r, b, l = fl

    draw.rectangle([l, t, r, b], outline="red", width=10)

pil_img.show()
