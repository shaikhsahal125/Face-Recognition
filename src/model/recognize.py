# from people import People
# from person import Person

import PIL.Image
import PIL.ImageDraw
from PIL import ImageFont
from random import randint as rand
import json
import face_recognition as fc
import sys

# ppl = People()
# ppl.addPerson(Person("Sahal", "/die/dke"))
# print(ppl)
# print(ppl.getPeople()[0].getName())

face_input = sys.argv[1]

# print(face_input)


# load image path as an image
image = fc.load_image_file(face_input)

# find faces
face_locations = fc.face_locations(image, number_of_times_to_upsample=1)
print(f"{len(face_locations)} faces were found")

# print(face_locations)

# encodings
img_encoding = fc.face_encodings(image, known_face_locations=face_locations)
# print(img_encoding)

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

recognized_names = []
counter = 0




known_faces_encodings = []
known_names = []

for key in facesDict:
    name, location = facesDict[key]
    known_names.append(name)
    known_faces_encodings.append(fc.face_encodings(fc.load_image_file(location)))

for face_en in img_encoding:

    for i, known_en in enumerate(known_faces_encodings):
        tol = 0.0
        while tol <= 0.5:
            result = fc.compare_faces(known_en, face_en, tolerance=tol)

            if result[0]:
                name = known_names[i]
                if name not in recognized_names:
                    recognized_names.append(name)
                    break
            tol += 0.1

print("\nRecognized faces")
print(recognized_names)
print()

name_list = recognized_names
detected = []
undetected = 0
myFont = ImageFont.truetype("Keyboard.ttf", 250)
for i, fl in enumerate(face_locations):
    print(fl)
    t, r, b, l = fl

    print(f"left Top: {(l,t)}")
    draw.rectangle([l, t, r, b], outline="red", width=10)
    try:
        detected.append(name_list[i])
    except:
        undetected += 1


txt = " ".join(detected)

if (undetected != 0):
    txt += f'\nAnd {undetected} unknown people'

draw.text((50, 50), txt, font=myFont, fill=(rand(0, 256), rand(0, 256), rand(0, 256)))


pil_img.show()
