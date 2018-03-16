package route.card

import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.bson.types.ObjectId
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import route.user.User

@RestController
class CardController {
    @RequestMapping(value = "/{userId}/cardpack", method = [RequestMethod.PUT])
    @ApiOperation(value = "Create a cardpack")
    @ApiResponses(
            ApiResponse(code = 200, message = "Cardpack created"),
            ApiResponse(code = 400, message = "Invalid request body"),
            ApiResponse(code = 404, message = "User does not exist")
    )
    fun createCardpack(@PathVariable userId: String, @RequestBody doc: JsonCardpack): ResponseEntity<Cardpack> {
        var user: User

        try {
            user = User.get(ObjectId(userId))
        } catch (e: Exception) {
            return ResponseEntity.notFound().build()
        }

        return try {
            val cardpack = Cardpack.create(doc.name!!, user)
            ResponseEntity.ok(cardpack)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

//    @RequestMapping(value = "/cardpack/{id}", method = [RequestMethod.GET])
//    fun getCardpack(@PathVariable id: String): ResponseEntity<Cardpack> {
//        return ResponseEntity.ok(Cardpack.get(ObjectId(id)))
//    }
//
//    @RequestMapping(value = "/cardpack/{id}", method = [RequestMethod.PATCH])
//    fun patchCardpack(@RequestBody patchDoc: List<Document>, @PathVariable id: String): ResponseEntity<*>? {
//        return null
//    }
//
//    @RequestMapping(value = "/cardpack/{id}", method = [RequestMethod.DELETE])
//    fun deleteCardpack(@RequestBody doc: Document, @PathVariable id: String): ResponseEntity<*>? {
//        return null
//    }
//
//    @RequestMapping(value = "/cardpack/{id}", method = [RequestMethod.PUT])
//    fun createCard(@RequestBody userDoc: Document, @PathVariable id: String): ResponseEntity<Card>? {
//        // TODO - Allow single card or array
//        return null
//    }
//
//    @RequestMapping(value = "/card/{id}", method = [RequestMethod.DELETE])
//    fun deleteCard(@PathVariable id: String): ResponseEntity<*> {
//        // TODO - Delete card
//        return ResponseEntity.ok(null)
//    }
//
//    @RequestMapping(value = "/cardpack/{id}/cards", method = [RequestMethod.GET])
//    fun getCards(@PathVariable id: String): ResponseEntity<List<Card>> {
//        return ResponseEntity.ok(Cardpack.get(ObjectId(id)).getCards())
//    }
//
//    @RequestMapping(value = "/cardpack/{cardpackId}/card/{cardId}", method = [RequestMethod.PATCH])
//    fun patchCard(@RequestBody patchDoc: List<Document>, @PathVariable cardpackId: String, @PathVariable cardId: String): ResponseEntity<*>? {
//        return null
//    }
//
//    @RequestMapping(value = "/cardpack/{cardpackId}/card/{cardId}", method = [RequestMethod.DELETE])
//    fun deleteCard(@PathVariable cardpackId: String, @PathVariable cardId: String): ResponseEntity<*>? {
//        return null
//    }
}
