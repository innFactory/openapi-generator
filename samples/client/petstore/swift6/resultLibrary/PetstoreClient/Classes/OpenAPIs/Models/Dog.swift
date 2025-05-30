//
// Dog.swift
//
// Generated by openapi-generator
// https://openapi-generator.tech
//

import Foundation

internal struct Dog: Sendable, Codable, ParameterConvertible {

    internal private(set) var className: String
    internal private(set) var color: String? = "red"
    internal private(set) var breed: String?

    internal init(className: String, color: String? = "red", breed: String? = nil) {
        self.className = className
        self.color = color
        self.breed = breed
    }

    internal enum CodingKeys: String, CodingKey, CaseIterable {
        case className
        case color
        case breed
    }

    // Encodable protocol methods

    internal func encode(to encoder: Encoder) throws {
        var container = encoder.container(keyedBy: CodingKeys.self)
        try container.encode(className, forKey: .className)
        try container.encodeIfPresent(color, forKey: .color)
        try container.encodeIfPresent(breed, forKey: .breed)
    }
}

