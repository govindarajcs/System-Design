FROM ubuntu

RUN apt-get update

RUN echo y | apt-get install python3.10

RUN echo y | apt install pip

RUN echo y | pip install flask

COPY . .

CMD [ "python3", "-m" , "flask", "run", "--host=0.0.0.0"]