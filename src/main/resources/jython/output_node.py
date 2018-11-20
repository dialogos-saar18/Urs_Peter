
from com.clt.diamant.graph.nodes import AbstractOutputNode
from com.clt.diamant.graph.nodes.AbstractOutputNode import DefaultPromptType
from com.clt.speech import SpeechException
from com.clt.speech.tts import VoiceName
from java.util import Collections
from java.util import List
from java.util import Map


class JythonStdoutOutputNode(AbstractOutputNode):
    def getResourceString(self, string):
        return string

    def getAvailableVoices(self):
        return [VoiceName("", None)]

    def speak(self, string, options):
        print string

    def stopSynthesis(self):
        pass


class JythonDoublingOutputNode(AbstractOutputNode):
    def getResourceString(self, string):
        return string

    def getAvailableVoices(self):
        return [VoiceName("", None)]

    def speak(self, string, options):
        print string
        print "xx"
        print string

    def stopSynthesis(self):
        pass


