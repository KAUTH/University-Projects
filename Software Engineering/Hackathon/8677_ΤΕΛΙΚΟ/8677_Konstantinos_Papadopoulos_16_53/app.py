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
node4 = Forker()
node3 = Joiner()
node7 = Talk()
node7.setParameters(sentence = "Hello, welcome to the library, I am ROLAS, your Robo Librarian Assistant System. Are you a guest or registerd user?", override = False, volume = 100)
vocabulary = []
vocabulary.append("Registered")
vocabulary.append("Guest")
node8 = Listen()
node8.setParameters(duration = -1, lexicon = vocabulary, sayOptions = False)
node9 = Talk()
node9.setParameters(sentence = "As a guest you only check the availability of books. There are two books in the library, Romeo and Legend.", override = False, volume = 100)
node10 = Talk()
node10.setParameters(sentence = "As a registered user you only check the description or the availability. Touch my head for the availability or my hand for the description. Select Romeo or Legend.", override = False, volume = 100)
vocabulary = []
vocabulary.append("Legend")
vocabulary.append("Romeo")
node11 = Listen()
node11.setParameters(duration = -1, lexicon = vocabulary, sayOptions = False)
node12 = Talk()
node12.setParameters(sentence = "Romeo and Juliet is not in the library.", override = False, volume = 100)
node13 = Talk()
node13.setParameters(sentence = "Legend is not in the library! ha ha", override = False, volume = 100)
node14 = Stop()
bodyParts = []
bodyParts.append("HEAD")
bodyParts.append("HANDS")
node15 = DetectTouch()
node15.setParameters(duration = -1, parts = bodyParts)
vocabulary = []
vocabulary.append("Legend")
vocabulary.append("Romeo")
node16 = Listen()
node16.setParameters(duration = -1, lexicon = vocabulary, sayOptions = False)
node18 = Talk()
node18.setParameters(sentence = "I have no idea what book Legend is.", override = False, volume = 100)
node21 = Forker()
node20 = Joiner()
vocabulary = []
vocabulary.append("Hey NAO")
node5_0 = Listen()
node5_0.setParameters(duration = -1, lexicon = vocabulary, sayOptions = False)
node5_GroupStart = Null()
node5_GroupFinish = Null()
node6_0 = DetectHuman()
node6_0.setParameters(duration = -1)
node6_GroupStart = Null()
node6_GroupFinish = Null()
node22_0 = Talk()
node22_0.setParameters(sentence = "Romeo and Juliet is a love story, bla bla bla", override = False, volume = 100)
node22_GroupStart = Null()
node22_GroupFinish = Null()
node23_0 = ArmMotion()
node23_0.setParameters(motionType = 'OPEN', hand = 'LEFT')
node23_1 = ArmMotion()
node23_1.setParameters(motionType = 'CLOSE', hand = 'LEFT')
node23_2 = ArmMotion()
node23_2.setParameters(motionType = 'OFFER', hand = 'LEFT')
node23_GroupStart = Null()
node23_GroupFinish = Null()

node5 = Composer()
node6 = Composer()
node22 = Composer()
node23 = Composer()
node5.setInternalStates([node5_GroupStart, node5_GroupFinish, node5_0])
node5.setInitialState(node5_0)
node6.setInternalStates([node6_GroupStart, node6_GroupFinish, node6_0])
node6.setInitialState(node6_0)
node22.setInternalStates([node22_GroupStart, node22_GroupFinish, node22_0])
node22.setInitialState(node22_0)
node23.setInternalStates([node23_GroupStart, node23_GroupFinish, node23_0, node23_1, node23_2])
node23.setInitialState(node23_0)

node2.setName(name = 'node2')
node3.setName(name = 'node3')
node4.setName(name = 'node4')
node5.setName(name = 'node5')
node6.setName(name = 'node6')
node7.setName(name = 'node7')
node8.setName(name = 'node8')
node9.setName(name = 'node9')
node10.setName(name = 'node10')
node11.setName(name = 'node11')
node12.setName(name = 'node12')
node13.setName(name = 'node13')
node14.setName(name = 'node14')
node15.setName(name = 'node15')
node16.setName(name = 'node16')
node18.setName(name = 'node18')
node20.setName(name = 'node20')
node21.setName(name = 'node21')
node22.setName(name = 'node22')
node23.setName(name = 'node23')
node5_0.setName(name = 'node5_0')
node5_GroupStart.setName(name = 'node5_GroupStart')
node5_GroupFinish.setName(name = 'node5_GroupFinish')
node6_0.setName(name = 'node6_0')
node6_GroupStart.setName(name = 'node6_GroupStart')
node6_GroupFinish.setName(name = 'node6_GroupFinish')
node22_0.setName(name = 'node22_0')
node22_GroupStart.setName(name = 'node22_GroupStart')
node22_GroupFinish.setName(name = 'node22_GroupFinish')
node23_0.setName(name = 'node23_0')
node23_1.setName(name = 'node23_1')
node23_2.setName(name = 'node23_2')
node23_GroupStart.setName(name = 'node23_GroupStart')
node23_GroupFinish.setName(name = 'node23_GroupFinish')



node2.setNextStates([node4])

onDetectDictionary = []
node2.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])

onDetectDictionary = []
node3.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node4.setNextStates([node3])
node4.setForkedStates([node5 , node6 ])
node3.setNextStates([node7])
node3.setJoinedStates([node5, node6])

onDetectDictionary = []
node4.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node5.setNextStates([node3])

onDetectDictionary = []
node5.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node6.setNextStates([node3])

onDetectDictionary = []
node6.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node7.setNextStates([node8])

onDetectDictionary = []
node7.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node8.setNextStates([node10, node9, node8])

onDetectDictionary = {"Registered": [], "Guest": []}

node8.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node9.setNextStates([node11])

onDetectDictionary = []
node9.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node10.setNextStates([node15])

onDetectDictionary = []
node10.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node11.setNextStates([node13, node12, node11])

onDetectDictionary = {"Legend": [], "Romeo": []}

node11.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node12.setNextStates([node14])

onDetectDictionary = []
node12.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node13.setNextStates([node14])

onDetectDictionary = []
node13.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node14.setNextStates(None)

onDetectDictionary = []
node14.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node15.setNextStates([node11, node16, node15])

bodyParts = []
onDetectDictionary = {"HEAD": [], "HANDS": []}

node15.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node16.setNextStates([node18, node21, node16])

onDetectDictionary = {"Legend": [], "Romeo": []}

node16.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node18.setNextStates([node14])

onDetectDictionary = []
node18.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])

onDetectDictionary = []
node20.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node21.setNextStates([node20])
node21.setForkedStates([node22 , node23 ])
node20.setNextStates([node14])
node20.setJoinedStates([node22, node23])

onDetectDictionary = []
node21.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node22.setNextStates([node20])

onDetectDictionary = []
node22.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node23.setNextStates([node20])

onDetectDictionary = []
node23.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node5_0.setNextStates([node5_GroupFinish, node5_0])

onDetectDictionary = {"Hey NAO": []}

node5_0.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node5_GroupStart.setNextStates([node5_0])

onDetectDictionary = []
node5_GroupStart.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node5_GroupFinish.setNextStates(None)

onDetectDictionary = []
node5_GroupFinish.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node6_0.setNextStates([node6_GroupFinish, node6_0])

onDetectDictionary = []
node6_0.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node6_GroupStart.setNextStates([node6_0])

onDetectDictionary = []
node6_GroupStart.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node6_GroupFinish.setNextStates(None)

onDetectDictionary = []
node6_GroupFinish.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node22_0.setNextStates([node22_GroupFinish])

onDetectDictionary = []
node22_0.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node22_GroupStart.setNextStates([node22_0])

onDetectDictionary = []
node22_GroupStart.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node22_GroupFinish.setNextStates(None)

onDetectDictionary = []
node22_GroupFinish.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node23_0.setNextStates([node23_1])

onDetectDictionary = []
node23_0.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node23_1.setNextStates([node23_2])

onDetectDictionary = []
node23_1.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node23_2.setNextStates([node23_GroupFinish])

onDetectDictionary = []
node23_2.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node23_GroupStart.setNextStates([node23_0])

onDetectDictionary = []
node23_GroupStart.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])
node23_GroupFinish.setNextStates(None)

onDetectDictionary = []
node23_GroupFinish.setPreemptions(onStart = [], onStop = [], onDetect = onDetectDictionary, onNotDetect = [])


node2.execute()

