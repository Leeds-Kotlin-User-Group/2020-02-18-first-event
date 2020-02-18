package com.example.reactivekotlindemo.demo

import com.example.reactivekotlindemo.structural.Failable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
class DemoController(private val demoRepository: DemoRepository) {
    @GetMapping("/demos/{id}")
    suspend fun getDemo(@PathVariable id: Int): ResponseEntity<Demo> {
        val demo = demoRepository.findById(id)
        return if(demo != null) {
            ResponseEntity.ok(demo)
        }
        else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/demos")
    suspend fun getDemos(): List<Demo> {
        return demoRepository.findAll()
    }

    @PostMapping("/demos")
    suspend fun save(@RequestBody demo: Demo): ResponseEntity<DemoResponse> {
        return demoRepository.save(demo).fold(
            successOp = { result ->
                if (demo.id == null) {
                    ResponseEntity.created(URI.create("/demos/${result.id}")).body(result)
                } else {
                    ResponseEntity.ok(result)
                }
            },
            failureOp = { ResponseEntity.badRequest().body(it) }
        )
    }
}