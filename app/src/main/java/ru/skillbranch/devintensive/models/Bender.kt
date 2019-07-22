package ru.skillbranch.devintensive.models

class Bender(var status: Status = Status.NORMAL, var question: Question = Question.NAME) {


    var numberOfFalseAnswers:Int = 0

    fun askQuestion(): String = when (question) {
        Question.NAME ->  Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question
    }


    fun listenAnswer(answer: String): Pair<String, Triple<Int, Int, Int>> {
        return if (question.validate(answer)) {
                    if (question.answers.contains((answer.toLowerCase()))) {
                        question = question.nextQuestion()
                        "Отлично - ты справился\n${question.question}" to status.color
                    } else {
                        numberOfFalseAnswers++
                        if (numberOfFalseAnswers == 3) {
                            status = Status.NORMAL
                            question = Question.NAME
                            numberOfFalseAnswers = 0
                            "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
                        } else {
                            status = status.nextStatus()
                            "Это неправильный ответ\n${question.question}" to status.color
                        }
                    }
            } else {
                "${question.validationMessage}\n${question.question}" to status.color
            }
    }



    enum class Status(val color : Triple<Int, Int, Int>) {
        NORMAL(Triple(255,255, 255)),
        WARNING(Triple(255,120, 0)),
        DANGER(Triple(255,60, 60)),
        CRITICAL(Triple(255,255, 0));

        fun nextStatus():Status{
            return if(this.ordinal<values().lastIndex){
                values()[this.ordinal+1]
            } else {
                values()[0]
            }
        }
    }

    enum class Question(val question: String, val answers: List<String>, val validationMessage : String) {
        NAME("Как меня зовут?", listOf("бендер", "bender"), "Имя должно начинаться с заглавной буквы") {
            override fun validate(answer: String): Boolean {
                var regularexpr = Regex("[A-Z|А-Я]+.*")
                return answer.matches(regularexpr)
            }
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender"), "Профессия должна начинаться со строчной буквы") {
            override fun validate(answer: String): Boolean {
                var regularexpr = Regex("[a-z|а-я]+.*")
                return answer.matches(regularexpr)
            }
        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood"), "Материал не должен содержать цифр") {
            override fun validate(answer: String): Boolean {
                var regularexpr = Regex("^([^0-9]*)\$")
                return answer.matches(regularexpr)
            }
        },
        BDAY("Когда меня создали?", listOf("2993"), "Год моего рождения должен содержать только цифры") {
            override fun validate(answer: String): Boolean {
                var regularexpr = Regex("^([0-9]*)\$")
                return answer.matches(regularexpr)
            }
        },
        SERIAL("Мой серийный номер?", listOf("2716057"), "Серийный номер содержит только цифры, и их 7") {
            override fun validate(answer: String): Boolean {
                var regularexpr = Regex("^([0-9]{7})\$")
                return answer.matches(regularexpr)
            }
        },
        IDLE("На этом все, вопросов больше нет", listOf(), "") {
            override fun validate(answer: String): Boolean {
                return true
            }
        };

        fun nextQuestion():Question{
            return if(this.ordinal<values().lastIndex){
                values()[this.ordinal+1]
            } else {
               this
            }
        }
        abstract fun validate(answer:String):Boolean
    }
}