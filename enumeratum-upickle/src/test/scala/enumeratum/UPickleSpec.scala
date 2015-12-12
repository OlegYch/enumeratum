package enumeratum

import org.scalatest._
import upickle.Js

/**
  * Created by Lloyd on 12/12/15.
  */
class UPickleSpec extends FunSpec with Matchers {

  import Dummy._

  describe("Reader") {

    val reader = UPickler.reader(Dummy)

    it("should work with valid values") {
      reader.read(Js.Str("A")) shouldBe A
    }

    it("should fail with invalid values") {
      intercept[Exception] {
        reader.read(Js.Str("D"))
      }
      intercept[Exception] {
        reader.read(Js.Num(2))
      }
    }

  }

  describe("insensitive reader") {
    val reader = UPickler.reader(Dummy, true)

    it("should work with strings, disgregarding case") {
      reader.read(Js.Str("A")) shouldBe A
      reader.read(Js.Str("a")) shouldBe A
    }

    it("should work with invalid values") {
      intercept[Exception](reader.read(Js.Str("D")))
      intercept[Exception](reader.read(Js.Num(5)))
    }

  }

  describe("Writer") {

    val writer = UPickler.writer(Dummy)

    it("should write enum values to JsString") {
      writer.write(A) shouldBe Js.Str("A")
    }

  }

}
