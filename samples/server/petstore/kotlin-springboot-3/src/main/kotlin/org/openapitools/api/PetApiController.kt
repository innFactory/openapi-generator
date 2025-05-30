package org.openapitools.api

import org.openapitools.model.ModelApiResponse
import org.openapitools.model.Pet
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

import org.springframework.web.bind.annotation.*
import org.springframework.validation.annotation.Validated
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.beans.factory.annotation.Autowired

import jakarta.validation.Valid
import jakarta.validation.constraints.DecimalMax
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

import kotlin.collections.List
import kotlin.collections.Map

@RestController
@Validated
class PetApiController(@Autowired(required = true) val service: PetApiService) {


    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/pet"],
        produces = ["application/xml", "application/json"],
        consumes = ["application/json", "application/xml"]
    )
    fun addPet( @Valid @RequestBody pet: Pet): ResponseEntity<Pet> {
        return ResponseEntity(service.addPet(pet), HttpStatus.valueOf(200))
    }


    @RequestMapping(
        method = [RequestMethod.DELETE],
        value = ["/pet/{petId}"]
    )
    fun deletePet( @PathVariable("petId") petId: kotlin.Long, @RequestHeader(value = "api_key", required = false) apiKey: kotlin.String?): ResponseEntity<Unit> {
        return ResponseEntity(service.deletePet(petId, apiKey), HttpStatus.valueOf(400))
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/pet/findByStatus"],
        produces = ["application/xml", "application/json"]
    )
    fun findPetsByStatus( @RequestParam(value = "status", required = true) status: kotlin.collections.List<kotlin.String>): ResponseEntity<List<Pet>> {
        return ResponseEntity(service.findPetsByStatus(status), HttpStatus.valueOf(200))
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/pet/findByTags"],
        produces = ["application/xml", "application/json"]
    )
    fun findPetsByTags( @RequestParam(value = "tags", required = true) tags: kotlin.collections.List<kotlin.String>): ResponseEntity<List<Pet>> {
        return ResponseEntity(service.findPetsByTags(tags), HttpStatus.valueOf(200))
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/pet/{petId}"],
        produces = ["application/xml", "application/json"]
    )
    fun getPetById( @PathVariable("petId") petId: kotlin.Long): ResponseEntity<Pet> {
        return ResponseEntity(service.getPetById(petId), HttpStatus.valueOf(200))
    }


    @RequestMapping(
        method = [RequestMethod.PUT],
        value = ["/pet"],
        produces = ["application/xml", "application/json"],
        consumes = ["application/json", "application/xml"]
    )
    fun updatePet( @Valid @RequestBody pet: Pet): ResponseEntity<Pet> {
        return ResponseEntity(service.updatePet(pet), HttpStatus.valueOf(200))
    }


    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/pet/{petId}"],
        consumes = ["application/x-www-form-urlencoded"]
    )
    fun updatePetWithForm( @PathVariable("petId") petId: kotlin.Long, @Valid @RequestParam(value = "name", required = false) name: kotlin.String? , @Valid @RequestParam(value = "status", required = false) status: kotlin.String? ): ResponseEntity<Unit> {
        return ResponseEntity(service.updatePetWithForm(petId, name, status), HttpStatus.valueOf(405))
    }


    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/pet/{petId}/uploadImage"],
        produces = ["application/json"],
        consumes = ["multipart/form-data"]
    )
    fun uploadFile( @PathVariable("petId") petId: kotlin.Long, @Valid @RequestParam(value = "additionalMetadata", required = false) additionalMetadata: kotlin.String? , @Valid @RequestPart("file", required = false) file: org.springframework.web.multipart.MultipartFile?): ResponseEntity<ModelApiResponse> {
        return ResponseEntity(service.uploadFile(petId, additionalMetadata, file), HttpStatus.valueOf(200))
    }
}
