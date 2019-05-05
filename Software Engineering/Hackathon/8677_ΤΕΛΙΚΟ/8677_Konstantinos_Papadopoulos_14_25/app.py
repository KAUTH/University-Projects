#!/usr/bin/python2
# -*- coding: utf-8 -*-

import time

from Behaviors.BArmMotion import ArmMotion
from Behaviors.BBodyMove import BodyMove
from Behaviors.BComposer import Composer
from Behaviors.BCounter import Counter
from Behaviors.BDebug import DBG
from Behaviors.BDetectHuman import DetectHuman
from Behaviors.BDetectMotion import DetectMotion
from Behaviors.BDetectSound import DetectSound
from Behaviors.BDetectTouch import DetectTouch
from Behaviors.BForker import Forker
from Behaviors.BHeadMotion import HeadMotion
from Behaviors.BJoiner import Joiner
from Behaviors.BLearnMotion import LearnMotion
from Behaviors.BListen import Listen
from Behaviors.BPosture import Posture
from Behaviors.BRandomSelection import RandomSelection
from Behaviors.BReplayMotion import ReplayMotion
from Behaviors.BStop import Stop
from Behaviors.BTakePicture import TakePicture
from Behaviors.BTalk import Talk
from Behaviors.BWeatherReport import WeatherReport
from Behaviors.BDelay import Delay
from Behaviors.BNull import Null
from Behaviors.BLeds import Leds
from Behaviors.BPlayAudio import PlayAudio
from Behaviors.BRecord import Record

node2 = Posture()
node2.setParameters(posture = 'STAND')
node3 = Talk()
node3.setParameters(sentence = "Hello everybody, this is the first challenge!", override = False, volume = 80)
node4 = RandomSelection()
bodyParts = []
bodyParts.append("RIGHT_HAND")
bodyParts.append("HEAD")
node5 = DetectTouch()
node5.setParameters(duration = 5.0, parts = bodyParts)
vocabulary = []
vocabulary.append("Thessaloniki")
vocabulary.append("Athens")
nodeListenWeather6 = Listen()
nodeListenWeather6.setParameters(duration = -1, lexicon = vocabulary, sayOptions = True)
nodeWeatherThessaloniki6 = WeatherReport()
nodeWeatherThessaloniki6.setParameters(country = 'GR', city = 'Thessaloniki')
nodeWeatherAthens6 = WeatherReport()
nodeWeatherAthens6.setParameters(country = 'GR', city = 'Athens')
nodeWeatherTalk6 = Talk()
nodeWeatherTalk6.setParameters(sentence = '', override = True, volume = 80)
node7 = RandomSelection()
node8 = Talk()
node8.setParameters(sentence = "Goodbye, application terminated.", override = False, volume = 80)
node9 = Stop()
node10 = Talk()
node10.setParameters(sentence = "I will detect touch on right hand or head.", override = False, volume = 80)
node11 = Talk()
node11.setParameters(sentence = "You touched my right hand.", override = False, volume = 80)
node12 = Talk()
node12.setParameters(sentence = "You touched my head.", override = False, volume = 80)


node2.setName(name = 'node2')
node3.setName(name = 'node3')
node4.setName(name = 'node4')
node5.setName(name = 'node5')
nodeListenWeather6.setName(name = 'nodeListenWeather6')
nodeWeatherThessaloniki6.setName(name = 'nodeWeatherThessaloniki6')
nodeWeatherAthens6.setName(name = 'nodeWeatherAthens6')
nodeWeatherTalk6.setName(name = 'nodeWeatherTalk6')
node7.setName(name = 'node7')
node8.setName(name = 'node8')
node9.setName(name = 'node9')
node10.setName(name = 'node10')
node11.setName(name = 'node11')
node12.setName(name = 'node12')



node2.setNextStates([node3])

onDetectDictionary = []
node2.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node3.setNextStates([node4])

onDetectDictionary = []
node3.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node4.setNextStates([nodeListenWeather6, nodeListenWeather10])

onDetectDictionary = []
node4.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node5.setNextStates([node11, node12, node10])

bodyParts = []
onDetectDictionary = {"RIGHT_HAND": [], "HEAD": []}

node5.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
nodeListenWeather6.setNextStates([nodeWeatherThessaloniki6, nodeWeatherAthens6, nodeListenWeather6])
nodeWeatherThessaloniki6.setNextStates([nodeWeatherTalk6])
nodeWeatherAthens6.setNextStates([nodeWeatherTalk6])
nodeWeatherTalk6.setNextStates([node7])

vocabulary = []
onDetectDictionary = {"Thessaloniki": [], "Athens": []}
vocabulary.append("Thessaloniki")
vocabulary.append("Athens")
nodeListenWeather6.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
nodeWeatherThessaloniki6.setPreemptions(onStart = [], onStop = [], onDetect = [], onNotDetect = [])
nodeWeatherAthens6.setPreemptions(onStart = [], onStop = [], onDetect = [], onNotDetect = [])
nodeWeatherTalk6.setPreemptions(onStart = [], onStop = [], onDetect = [], onNotDetect = [])

node7.setNextStates([node3, node8])

onDetectDictionary = []
node7.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node8.setNextStates([node9])

onDetectDictionary = []
node8.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node9.setNextStates(None)

onDetectDictionary = []
node9.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node10.setNextStates([node5])

onDetectDictionary = []
node10.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node11.setNextStates([node7])

onDetectDictionary = []
node11.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node12.setNextStates([node7])

onDetectDictionary = []
node12.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])


node2.execute()

