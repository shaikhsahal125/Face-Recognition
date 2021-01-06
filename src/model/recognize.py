from people import People
from person import Person

ppl = People()
ppl.addPerson(Person("Sahal", "/die/dke"))
print(ppl)
print(ppl.getPeople()[0].getName())
